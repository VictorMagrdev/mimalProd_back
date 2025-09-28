package com.example.minimal_prod_backend.steps;

import com.example.minimal_prod_backend.entity.Rol;
import com.example.minimal_prod_backend.entity.Usuario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagementStepdefs extends BaseStepdefs {

    

    

    @Then("la respuesta debe tener el código de estado {int}")
    public void laRespuestaDebeTenerElCódigoDeEstado(int statusCode) {
        assertNotNull(response, "No se recibió respuesta del endpoint");
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @And("el cuerpo de la respuesta debe contener los detalles del usuario {string}")
    public void elCuerpoDeLaRespuestaDebeContenerLosDetallesDelUsuario(String username) {
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(username));
    }

    @When("envío una petición GET a {string}")
    public void envíoUnaPeticiónGETA(String path) {
        makeRequest("GET", path, null);
    }

    @And("el cuerpo de la respuesta debe ser una lista de usuarios")
    public void elCuerpoDeLaRespuestaDebeSerUnaListaDeUsuarios() {
        assertNotNull(response.getBody());
        assertTrue(response.getBody().trim().startsWith("["));
    }

    @Given("existe un usuario con id {int}")
    public void existeUnUsuarioConId(int id) {
        userRepository.save(Usuario.builder().id((long)id).username("user"+id).password(passwordEncoder.encode("password")).email("user"+id+"@example.com").build());
    }

    @And("el cuerpo de la respuesta debe contener el email actualizado {string}")
    public void elCuerpoDeLaRespuestaDebeContenerElEmailActualizado(String email) {
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(email));
    }

    @And("el cuerpo de la respuesta debe contener el mensaje {string}")
    public void elCuerpoDeLaRespuestaDebeContenerElMensaje(String message) {
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(message));
    }

    @Given("existe un usuario con id {int} y un rol con id {int}")
    public void existeUnUsuarioConIdYUnRolConId(int userId, int roleId) {
        existeUnUsuarioConId(userId);
        roleRepository.save(Rol.builder().id((long)roleId).name("role"+roleId).build());
    }

    @Given("un usuario con id {int} tiene asignado el rol con id {int}")
    public void unUsuarioConIdTieneAsignadoElRolConId(int userId, int roleId) {
        Rol role = roleRepository.findById((long)roleId).orElseGet(() -> roleRepository.save(Rol.builder().id((long)roleId).name("role"+roleId).build()));
        Usuario usuario = userRepository.findById((long)userId).orElseGet(() -> userRepository.save(Usuario.builder().id((long)userId).username("user"+userId).password(passwordEncoder.encode("password")).email("user"+userId+"@example.com").build()));
        usuario.getRoles().add(role);
        userRepository.save(usuario);
    }

    @When("envío una petición DELETE a {string}")
    public void envíoUnaPeticiónDELETEA(String path) {
        makeRequest("DELETE", path, null);
    }
}
