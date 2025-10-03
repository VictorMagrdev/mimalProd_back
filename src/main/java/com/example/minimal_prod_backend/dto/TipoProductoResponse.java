package com.example.minimal_prod_backend.dto;

public record TipoProductoResponse(
        Long id,
        String codigo,
        String nombre,
        String descripcion
) {
}