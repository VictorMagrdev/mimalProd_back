package com.example.minimal_prod_backend.dto;

public record PermisoResponse(
        Long id,
        String accion,
        String descripcion
) {}