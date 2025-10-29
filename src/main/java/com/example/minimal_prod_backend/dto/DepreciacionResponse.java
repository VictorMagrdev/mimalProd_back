package com.example.minimal_prod_backend.dto;

import com.example.minimal_prod_backend.entity.TipoPeriodo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record DepreciacionResponse(
        Long id,
        Long maquinaId,
        TipoPeriodo tipoPeriodo,
        LocalDate periodo,
        BigDecimal depreciacionPeriodo,
        BigDecimal depreciacionAcumulada,
        BigDecimal valorNeto,
        OffsetDateTime calculadoEn
) {
}
