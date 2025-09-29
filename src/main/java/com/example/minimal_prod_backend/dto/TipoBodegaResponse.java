package com.example.minimal_prod_backend.dto;

public record TipoBodegaResponse(
        Long id,
        String codigo,
        String nombre,
        String descripcion
) {}
