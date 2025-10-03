package com.example.minimal_prod_backend.dto;

public record UnidadMedidaTipoRequest(
        String codigo,
        String nombre,
        String descripcion
) {
}