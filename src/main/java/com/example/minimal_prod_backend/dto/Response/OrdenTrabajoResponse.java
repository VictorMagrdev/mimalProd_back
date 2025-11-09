package com.example.minimal_prod_backend.dto.Response;

import java.time.OffsetDateTime;

public record OrdenTrabajoResponse(
        Long id,
        String referencia,
        Long tipoId,
        String descripcion,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        Long estadoId,
        OffsetDateTime creadoEn
) {
}