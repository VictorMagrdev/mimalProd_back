package com.example.minimal_prod_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ConteoCiclicoRequest(
        Long productoId,
        Long bodegaId,
        Long loteId,
        @NotNull @Positive BigDecimal cantidadContada,
        Long unidadId,
        OffsetDateTime fecha
) {
}