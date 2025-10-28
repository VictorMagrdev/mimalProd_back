package com.example.minimal_prod_backend.dto;

public record EstadoIncidenciaRequest(
        String nombre,
        String descripcion,
        Integer orden,
        Boolean estadoFinal
) {}