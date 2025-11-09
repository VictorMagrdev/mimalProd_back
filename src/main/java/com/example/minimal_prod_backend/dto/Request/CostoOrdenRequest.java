package com.example.minimal_prod_backend.dto.Request;

import java.math.BigDecimal;

public record CostoOrdenRequest(
        Long ordenId,
        Long tipoCostoId,
        String descripcion,
        BigDecimal monto,
        String moneda
) {
}