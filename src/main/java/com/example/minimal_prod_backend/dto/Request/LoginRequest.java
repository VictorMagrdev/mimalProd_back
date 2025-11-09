package com.example.minimal_prod_backend.dto.Request;

public record LoginRequest(
        String username,
        String password
) {
}