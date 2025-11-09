package com.example.minimal_prod_backend.dto.Request;

public record TipoProductoRequest(
        String codigo,
        String nombre,
        String descripcion
) {
}