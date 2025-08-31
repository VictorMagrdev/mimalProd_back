package com.example.rbac.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthStepdefs {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;
    private String currentToken;

    @When("{string} inicia sesión con contraseña {string}")
    public void iniciaSesiónConContraseña(String username, String password) {
        String requestBody = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            response = restTemplate.postForEntity("/auth/login", entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }

    @Then("recibe un token JWT válido")
    public void recibeUnTokenJWTVálido() {
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe retornar 200 OK");
        assertNotNull(response.getBody(), "La respuesta debe contener el token");

        String token = response.getBody();
        assertTrue(token.split("\\.").length == 3, "El token debe tener 3 partes (JWT)");

        currentToken = token;
    }

    @Then("recibe un error de autenticación {string}")
    public void recibeUnErrorDeAutenticación(String expectedError) {
        assertNotNull(response, "No se recibió respuesta del endpoint");

        if (expectedError.contains("inactivo") || expectedError.contains("deshabilitado")) {
            assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode(),
                    "El status debe ser 403 Forbidden para usuario desactivado");
        } else {
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode(),
                    "El status debe ser 401 Unauthorized para credenciales incorrectas");
        }

        String body = response.getBody();
        assertNotNull(body, "El body no debería ser nulo");
        assertTrue(
                body.contains(expectedError),
                String.format("Se esperaba que el body contenga '%s', pero fue: %s", expectedError, body));
    }

    @And("el usuario {string} está desactivado")
    public void elUsuarioEstáDesactivado(String username) {
        obtenerTokenAdmin();

        String deactivateUrl = "/api/admin/users/" + username + "/deactivate";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> deactivateResponse = restTemplate.exchange(
                deactivateUrl, HttpMethod.PUT, entity, String.class
        );

        assertEquals(HttpStatus.OK, deactivateResponse.getStatusCode(),
                "Fallo al desactivar usuario: " + deactivateResponse.getStatusCode());
    }

    @When("{string} intenta iniciar sesión con contraseña {string}")
    public void intentaIniciarSesiónConContraseña(String username, String password) {
        iniciaSesiónConContraseña(username, password);
    }

    @Given("{string} tiene un token JWT válido")
    public void tieneUnTokenJWTVálido(String username) {
        if ("admin".equals(username)) {
            iniciaSesiónConContraseña("admin", "AdminPass123");
        } else {
            iniciaSesiónConContraseña(username, "secret");
        }

        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Login falló al obtener token para usuario: " + username);
        currentToken = response.getBody();
    }

    @When("{string} realiza logout con su token")
    public void realizaLogoutConSuToken(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange("/auth/logout", HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }

    @Then("ese token ya no permite acceder a recursos protegidos")
    public void eseTokenYaNoPermiteAccederARecursosProtegidos() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> protectedResponse = restTemplate.exchange(
                    "/api/protected", HttpMethod.GET, entity, String.class
            );
            fail("El token debería estar invalidado, pero se obtuvo: " + protectedResponse.getStatusCode());
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.UNAUTHORIZED, e.getStatusCode(),
                    "Debería recibir 401 Unauthorized con token invalidado");
        }
    }

    @Given("existe un usuario {string} con contraseña {string} y rol {string}")
    public void existeUnUsuarioConContraseñaYRol(String username, String password, String role) {
        obtenerTokenAdmin();

        String createUserUrl = "/api/admin/users";

        String requestBody = String.format(
                "{\"username\":\"%s\", \"password\":\"%s\", \"role\":\"%s\", \"enabled\":true}",
                username, password, role
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> createResponse = restTemplate.postForEntity(
                createUserUrl, entity, String.class
        );

        assertTrue(createResponse.getStatusCode().is2xxSuccessful(),
                "Fallo al crear usuario " + username + ": " + createResponse.getStatusCode());
    }

    @And("el usuario {string} está activado")
    public void elUsuarioEstáActivado(String username) {
        obtenerTokenAdmin();

        String activateUrl = "/api/admin/users/" + username + "/activate";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> activateResponse = restTemplate.exchange(
                activateUrl, HttpMethod.PUT, entity, String.class
        );

        assertEquals(HttpStatus.OK, activateResponse.getStatusCode(),
                "Fallo al activar usuario: " + activateResponse.getStatusCode());
    }

    private void obtenerTokenAdmin() {
        iniciaSesiónConContraseña("admin", "AdminPass123");
        if (response.getStatusCode() != HttpStatus.OK) {
            fail("No se pudo obtener token de administrador");
        }
        currentToken = response.getBody();
    }
}