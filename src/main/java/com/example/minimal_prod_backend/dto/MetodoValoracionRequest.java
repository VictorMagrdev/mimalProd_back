package com.example.minimal_prod_backend.dto;

public record MetodoValoracionRequest(
        String codigo,
        String nombre,
        String descripcion
) {}