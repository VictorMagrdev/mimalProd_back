package com.example.minimal_prod_backend.dto;

public record LoginResponse(String token, String username, java.util.List<String> roles, java.util.List<PolicyResponse> policies) {
}