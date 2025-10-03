package com.example.minimal_prod_backend.dto;

public record TipoProductoRequest(
        String codigo,
        String nombre,
        String descripcion
) {
}