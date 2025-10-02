package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record OrdenEstacionResponse(
        Long id,
        Long ordenId,
        Long estacionId,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        OffsetDateTime inicioReal,
        OffsetDateTime finReal,
        Long estadoOrdenEstacionId,
        String observaciones
) {}