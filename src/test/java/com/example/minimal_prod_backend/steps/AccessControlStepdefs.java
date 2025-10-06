package com.example.minimal_prod_backend.steps;

import com.example.minimal_prod_backend.entity.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccessControlStepdefs extends BaseStepdefs {

    @Given("un usuario {string} tiene el rol {string}")
    public void unUsuarioTieneElRol(String username, String roleName) {
        Rol role = roleRepository.findByName(roleName).orElseGet(() -> roleRepository.save(Rol.builder().nombre(roleName).build()));
        Usuario usuario = Usuario.builder()
                .username(username)
                .password(passwordEncoder.encode("password"))
                .email(username + "@example.com")
                .roles(new HashSet<>(Set.of(role)))
                .build();
        userRepository.save(usuario);
    }

    @Given("el rol {string} tiene permiso para {string} en {string}")
    public void elRolTienePermisoParaEn(String roleName, String action, String tagName) {
        Rol role = roleRepository.findByName(roleName).orElseThrow();
        Tag tag = tagRepository.findByNombre(tagName).orElseGet(() -> tagRepository.save(Tag.builder().nombre(tagName).build()));
        Permiso permiso = permissionRepository.findByAccion(action).orElseGet(() -> permissionRepository.save(Permiso.builder().accion(action).build()));

        policyRepository.save(Politica.builder().rol(role).tag(tag).permiso(permiso).build());
    }

    @Given("{string} está autenticado")
    public void estaAutenticado(String username) {
        login(username, "password");
    }

    @Given("el rol {string} NO tiene permiso para {string} en {string}")
    public void elRolNOTienePermisoParaEn(String roleName, String action, String tagName) {
        // No policy is created, so the permission does not exist.
    }

    @When("envío una petición POST a {string} con un cuerpo válido")
    public void envioUnaPeticionPOSTAConUnCuerpoValido(String path) {
        String body = "{\"username\":\"newuser\",\"email\":\"newuser@example.com\",\"password\":\"password\",\"roleIds\":[]}";
        makeRequest("POST", path, body);
    }
    
    @Then("la respuesta debe tener el código de estado {int}")
    public void laRespuestaDebeTenerElCodigoDeEstado(int statusCode) {
        assertNotNull(response, "No se recibió respuesta del endpoint");
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @Given("existe un rol con id {long}")
    public void existeUnRolConId(long id) {
        roleRepository.save(Rol.builder().id(id).nombre("role" + id).build());
    }

    @When("envío una petición DELETE a {string}")
    public void envioUnaPeticionDELETEA(String path) {
        makeRequest("DELETE", path, null);
    }

    @And("el rol {string} pierde el permiso para {string} en {string}")
    public void elRolPierdeElPermisoParaEn(String roleName, String action, String tagName) {
        Rol role = roleRepository.findByName(roleName).orElseThrow();
        Tag tag = tagRepository.findByNombre(tagName).orElseThrow();
        Permiso permiso = permissionRepository.findByAccion(action).orElseThrow();
        policyRepository.findByRolAndTagAndPermiso(role, tag, permiso).ifPresent(policyRepository::delete);
    }
}
