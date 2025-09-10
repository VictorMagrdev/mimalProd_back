package com.example.minimal_prod_backend.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationStepdefs extends BaseStepdefs {

    @Given("un usuario existe con el nombre de usuario {string} y la contraseña {string}")
    public void unUsuarioExisteConElNombreDeUsuarioYLaContraseña(String username, String password) {
        userRepository.save(com.example.minimal_prod_backend.entity.User.builder().username(username).password(passwordEncoder.encode(password)).email(username + "@example.com").build());
    }

    @Given("un usuario inactivo existe con el nombre de usuario {string} y la contraseña {string}")
    public void unUsuarioInactivoExiste(String username, String password) {
        userRepository.save(com.example.minimal_prod_backend.entity.User.builder().username(username).password(passwordEncoder.encode(password)).email(username + "@example.com").active(false).build());
    }

    

    @And("el cuerpo de la respuesta debe contener un token JWT")
    public void elCuerpoDeLaRespuestaDebeContenerUnTokenJWT() {
        assertNotNull(response.getBody());
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            assertTrue(root.has("token"));
            assertNotNull(root.get("token").asText());
        } catch (JsonProcessingException e) {
            fail("Error parsing JSON response: " + e.getMessage());
        }
    }

    @And("el cuerpo de la respuesta debe contener el mensaje de error {string}")
    public void elCuerpoDeLaRespuestaDebeContenerElMensajeDeError(String message) {
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(message));
    }
}
