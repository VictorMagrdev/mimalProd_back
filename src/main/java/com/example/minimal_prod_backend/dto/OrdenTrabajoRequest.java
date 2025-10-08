package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record OrdenTrabajoRequest(
        String referencia,
        Long tipoId,
        String descripcion,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        Long estadoId
) {
}