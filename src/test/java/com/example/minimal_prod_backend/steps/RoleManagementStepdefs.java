package com.example.minimal_prod_backend.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class RoleManagementStepdefs extends BaseStepdefs {

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

    @And("el cuerpo de la respuesta debe contener los detalles del rol {string}")
    public void elCuerpoDeLaRespuestaDebeContenerLosDetallesDelRol(String roleName) {
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(roleName));
    }

    @When("envío una petición GET a {string}")
    public void envíoUnaPeticiónGETA(String path) {
        makeRequest("GET", path, null);
    }

    @And("el cuerpo de la respuesta debe ser una lista de roles")
    public void elCuerpoDeLaRespuestaDebeSerUnaListaDeRoles() {
        assertNotNull(response.getBody());
        assertTrue(response.getBody().trim().startsWith("["));
    }

    @Given("existe un rol con id {int}")
    public void existeUnRolConId(int id) {
        roleRepository.save(com.example.minimal_prod_backend.entity.Role.builder().id((long)id).name("role"+id).build());
    }

    @When("envío una petición PUT a {string} con el cuerpo:")
    public void envíoUnaPeticiónPUTAConElCuerpo(String path, String body) {
        makeRequest("PUT", path, body);
    }

    @And("el cuerpo de la respuesta debe contener el nombre actualizado {string}")
    public void elCuerpoDeLaRespuestaDebeContenerElNombreActualizado(String name) {
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(name));
    }

    @When("envío una petición DELETE a {string}")
    public void envíoUnaPeticiónDELETEA(String path) {
        makeRequest("DELETE", path, null);
    }
}
