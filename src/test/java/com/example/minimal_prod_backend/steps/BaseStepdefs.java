package com.example.minimal_prod_backend.steps;

import com.example.minimal_prod_backend.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.fail;

public class BaseStepdefs {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected PolicyRepository policyRepository;

    @Autowired
    protected TagRepository tagRepository;

    @Autowired
    protected PermissionRepository permissionRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    protected ResponseEntity<String> response;
    protected String token;
    protected final ObjectMapper objectMapper = new ObjectMapper();

    @After
    public void cleanUp() {
        policyRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
        tagRepository.deleteAll();
        permissionRepository.deleteAll();
    }

    protected void makeRequest(String method, String path, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (token != null) {
            headers.setBearerAuth(token);
        }

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        HttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());

        try {
            response = restTemplate.exchange(path, httpMethod, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }

    protected void login(String username, String password) {
        String requestBody = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> loginResponse;
        try {
            loginResponse = restTemplate.postForEntity("/api/auth/login", entity, String.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {
                try {
                    JsonNode root = objectMapper.readTree(loginResponse.getBody());
                    this.token = root.get("token").asText();
                } catch (JsonProcessingException e) {
                    fail("Failed to parse login response: " + e.getMessage());
                }
            }
        } catch (HttpClientErrorException e) {
            // Handle login failure
        }
    }
}
