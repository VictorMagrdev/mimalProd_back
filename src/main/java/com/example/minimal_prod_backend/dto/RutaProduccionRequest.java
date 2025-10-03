package com.example.minimal_prod_backend.dto;

public record RutaProduccionRequest(
        Long productoId,
        String version,
        String nombre,
        Boolean activo
) {}