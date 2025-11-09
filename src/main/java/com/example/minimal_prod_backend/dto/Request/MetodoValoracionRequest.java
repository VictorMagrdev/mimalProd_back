package com.example.minimal_prod_backend.dto.Request;

public record MetodoValoracionRequest(
        String codigo,
        String nombre,
        String descripcion
) {
}