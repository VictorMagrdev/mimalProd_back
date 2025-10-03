package com.example.minimal_prod_backend.dto;

public record BodegaRequest(
        String codigo,
        String nombre,
        String descripcion,
        Long tipoBodegaId
) {
}
