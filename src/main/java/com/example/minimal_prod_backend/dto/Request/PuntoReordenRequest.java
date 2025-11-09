package com.example.minimal_prod_backend.dto.Request;

import java.math.BigDecimal;

public record PuntoReordenRequest(
        Long productoId,
        BigDecimal stockMinimo,
        BigDecimal stockSeguridad,
        Long unidadId
) {
}