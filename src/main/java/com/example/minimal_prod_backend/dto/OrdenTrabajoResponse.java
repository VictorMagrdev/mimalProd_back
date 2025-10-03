package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record OrdenTrabajoResponse(
        Long id,
        String referencia,
        Integer tipoId,
        String descripcion,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        Integer estadoId,
        OffsetDateTime creadoEn
) {
}