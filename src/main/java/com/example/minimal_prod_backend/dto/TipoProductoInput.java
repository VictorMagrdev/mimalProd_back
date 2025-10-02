package com.example.minimal_prod_backend.dto;

public record TipoProductoInput(
        String codigo,
        String nombre,
        String descripcion
) {}