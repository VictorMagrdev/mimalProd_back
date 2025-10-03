package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record LineaOrdenResponse(
        Long id,
        Long ordenId,
        Integer numeroLinea,
        Long productoComponenteId,
        BigDecimal cantidadRequerida,
        Long unidadComponenteId,
        BigDecimal cantidadUsada,
        BigDecimal costoUnitario,
        BigDecimal costoTotal,
        String observaciones,
        OffsetDateTime creadoEn
) {
}