package com.example.minimal_prod_backend.dto.Request;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record OrdenProduccionRequest(
        String numeroOrden,
        BigDecimal cantidad,
        Long unidadId,
        Long estadoId,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        OffsetDateTime inicioReal,
        OffsetDateTime finReal,
        BigDecimal cantidadDesperdicio,
        BigDecimal cantidadProducida,
        String observaciones
) {
}