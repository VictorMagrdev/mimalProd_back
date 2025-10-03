package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record UnidadConversionRequest(
        Long origenId,
        Long destinoId,
        BigDecimal factor
) {
}