package com.example.minimal_prod_backend.dto.Request;

import java.time.OffsetDateTime;

public record OrdenEstacionRequest(
        Long ordenId,
        Long estacionId,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        OffsetDateTime inicioReal,
        OffsetDateTime finReal,
        Long estadoOrdenEstacionId,
        String observaciones
) {
}