package com.example.minimal_prod_backend.dto;

import java.time.Duration;
import java.time.OffsetDateTime;

public record IncidenciaResponse(
        Long id,
        String codigo,
        String titulo,
        String descripcion,
        String tipoIncidencia,
        String estado,
        OffsetDateTime fechaCierre,
        Duration tiempoParada,
        OffsetDateTime creadoEn
) {
}
