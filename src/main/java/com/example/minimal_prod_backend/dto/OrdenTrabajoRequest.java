package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record OrdenTrabajoRequest(
        String referencia,
        Integer tipoId,
        String descripcion,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        Integer estadoId
) {}