package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record UnidadConversionResponse(
        Long id,
        Long origenId,
        Long destinoId,
        BigDecimal factor
) {}