package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CostoOrdenResponse(
        Long id,
        Long ordenId,
        Long tipoCostoId,
        String descripcion,
        BigDecimal monto,
        String moneda,
        OffsetDateTime registradoEn
) {}