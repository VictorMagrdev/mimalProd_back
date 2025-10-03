package com.example.minimal_prod_backend.dto;

public record LoginRequest(
        String username,
        String password
) {
}