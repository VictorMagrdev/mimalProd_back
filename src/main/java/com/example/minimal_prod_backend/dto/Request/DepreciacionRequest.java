package com.example.minimal_prod_backend.dto.Request;

import com.example.minimal_prod_backend.entity.TipoPeriodo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DepreciacionRequest(
        @NotNull Long maquinaId,
        @NotNull TipoPeriodo tipoPeriodo,
        @NotNull LocalDate periodo,
        @NotNull @Positive BigDecimal depreciacionPeriodo,
        @NotNull @Positive BigDecimal depreciacionAcumulada,
        @NotNull @Positive BigDecimal valorNeto
) {}