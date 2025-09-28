package com.example.minimal_prod_backend.steps;

import com.example.minimal_prod_backend.entity.Permiso;
import com.example.minimal_prod_backend.entity.Politica;
import com.example.minimal_prod_backend.entity.Rol;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class PolicyManagementStepdefs extends BaseStepdefs {

    @Given("estoy autenticado como {string} con rol {string}")
    public void estoyAutenticadoComoConRol(String username, String roleName) {
        login(username, "password");
    }

    @When("envío una petición POST a {string} con el cuerpo:")
    public void envíoUnaPeticiónAConElCuerpo(String path, String body) {
        makeRequest("POST", path, body);
    }

    @Then("la respuesta debe tener el código de estado {int}")
    public void laRespuestaDebeTenerElCódigoDeEstado(int statusCode) {
        assertNotNull(response, "No se recibió respuesta del endpoint");
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @And("el cuerpo de la respuesta debe contener los detalles de la política creada")
    public void elCuerpoDeLaRespuestaDebeContenerLosDetallesDeLaPolíticaCreada() {
        assertNotNull(response.getBody());
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            assertTrue(root.has("id"));
            assertTrue(root.has("role"));
            assertTrue(root.has("tag"));
        } catch (JsonProcessingException e) {
            fail("Error parsing JSON response: " + e.getMessage());
        }
    }

    @When("envío una petición GET a {string}")
    public void envíoUnaPeticiónGETA(String path) {
        makeRequest("GET", path, null);
    }

    @And("el cuerpo de la respuesta debe ser una lista de políticas")
    public void elCuerpoDeLaRespuestaDebeSerUnaListaDePolíticas() {
        assertNotNull(response.getBody());
        assertTrue(response.getBody().trim().startsWith("["));
    }

    @Given("existe una política con id {int}")
    public void existeUnaPoliticaConId(int id) {
        Rol role = roleRepository.save(Rol.builder().name("roleForPolicy"+id).build());
        com.example.minimal_prod_backend.entity.Tag tag = tagRepository.save(com.example.minimal_prod_backend.entity.Tag.builder().name("tagForPolicy"+id).build());
        Permiso permiso = permissionRepository.save(Permiso.builder().action("READ").build());
        policyRepository.save(Politica.builder().id((long)id).role(role).tag(tag).permiso(permiso).build());
    }

    @When("envío una petición DELETE a {string}")
    public void envíoUnaPeticiónDELETEA(String path) {
        makeRequest("DELETE", path, null);
    }
}
