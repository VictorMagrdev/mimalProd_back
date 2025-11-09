package com.example.minimal_prod_backend.dto.Response;

public record TagResponse(
        Long id,
        String nombre,
        String descripcion
) {
}