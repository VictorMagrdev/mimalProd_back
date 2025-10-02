package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record OrdenProduccionResponse(
        Long id,
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
        Long creadoPor,
        String observaciones,
        OffsetDateTime creadoEn,
        OffsetDateTime actualizadoEn
) {}