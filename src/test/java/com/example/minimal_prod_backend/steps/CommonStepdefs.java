package com.example.minimal_prod_backend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommonStepdefs extends BaseStepdefs {

    @Then("la respuesta debe tener el código de estado {int}")
    public void laRespuestaDebeTenerElCódigoDeEstado(int statusCode) {
        assertNotNull(response, "No se recibió respuesta del endpoint");
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @When("envío una petición {word} a {string} con el cuerpo:")
    public void envíoUnaPeticiónAConElCuerpo(String method, String path, String body) {
        makeRequest(method, path, body);
    }

    @When("envío una petición GET a {string}")
    public void envíoUnaPeticiónGETA(String path) {
        makeRequest("GET", path, null);
    }

    @When("envío una petición DELETE a {string}")
    public void envíoUnaPeticiónDELETEA(String path) {
        makeRequest("DELETE", path, null);
    }

    @Given("estoy autenticado como {string} con rol {string}")
    public void estoyAutenticadoComoConRol(String username, String roleName) {
        login(username, "password");
    }
}
