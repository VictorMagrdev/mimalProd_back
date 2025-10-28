package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record SeguimientoIncidenciaResponse(
        Long id,
        String incidenciaCodigo,
        String estadoAnterior,
        String estadoNuevo,
        String comentario,
        OffsetDateTime realizadoEn
) {}
