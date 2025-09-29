package com.example.minimal_prod_backend.dto;

public record TipoMovimientoRequest(
        String codigo,
        String nombre,
        String descripcion,
        Boolean afectaWip
) {}
