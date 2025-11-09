package com.example.minimal_prod_backend.dto.Response;

public record MetodoValoracionResponse(
        Long id,
        String codigo,
        String nombre,
        String descripcion
) {
}