package com.example.minimal_prod_backend.dto.Request;

import java.math.BigDecimal;

public record LineaOrdenRequest(
        Long ordenId,
        Integer numeroLinea,
        Long productoComponenteId,
        BigDecimal cantidadRequerida,
        Long unidadComponenteId,
        BigDecimal cantidadUsada,
        BigDecimal costoUnitario,
        String observaciones
) {
}