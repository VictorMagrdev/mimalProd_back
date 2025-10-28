package com.example.minimal_prod_backend.dto;


public record SeguimientoIncidenciaRequest(
        Long incidenciaId,
        Long estadoAnteriorId,
        Long estadoNuevoId,
        String comentario
) {}
