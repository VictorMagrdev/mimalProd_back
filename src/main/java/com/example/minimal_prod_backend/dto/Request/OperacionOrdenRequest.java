package com.example.minimal_prod_backend.dto.Request;

import java.time.OffsetDateTime;

public record OperacionOrdenRequest(
        Long ordenId,
        Long operacionRutaId,
        Long estacionId,
        Integer secuencia,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        OffsetDateTime inicioReal,
        OffsetDateTime finReal,
        Long estadoId,
        String observaciones
) {
}
