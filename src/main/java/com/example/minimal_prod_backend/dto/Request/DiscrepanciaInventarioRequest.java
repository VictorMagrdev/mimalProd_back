package com.example.minimal_prod_backend.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DiscrepanciaInventarioRequest(
        Long conteoId,
        @NotNull @Positive BigDecimal cantidadSistema,
        Boolean resuelto
) {}