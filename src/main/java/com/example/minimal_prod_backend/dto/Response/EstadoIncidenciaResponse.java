package com.example.minimal_prod_backend.dto.Response;

public record EstadoIncidenciaResponse(
        Long id,
        String nombre,
        String descripcion,
        Integer orden,
        Boolean estadoFinal
) {
}