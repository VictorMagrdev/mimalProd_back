package com.example.minimal_prod_backend.dto.Request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ParametroPlanificacionRequest(
        @NotNull(message = "El producto es requerido")
        Long productoId,

        Integer leadTimeDias,

        BigDecimal loteMinimo,

        BigDecimal loteEconomico,

        String politicaInventario
) {
}
