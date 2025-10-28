package com.example.minimal_prod_backend.dto;

public record EstadoIncidenciaResponse(
        Long id,
        String nombre,
        String descripcion,
        Integer orden,
        Boolean estadoFinal
) {}