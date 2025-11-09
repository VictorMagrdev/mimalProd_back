package com.example.minimal_prod_backend.dto.Request;

public record UnidadMedidaTipoRequest(
        String codigo,
        String nombre,
        String descripcion
) {
}