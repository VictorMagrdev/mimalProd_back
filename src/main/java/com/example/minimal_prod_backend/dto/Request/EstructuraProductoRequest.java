package com.example.minimal_prod_backend.dto.Request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record EstructuraProductoRequest(
        @NotNull(message = "El producto padre es requerido")
        Long productoPadreId,

        @NotNull(message = "El producto hijo es requerido")
        Long productoHijoId,

        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser positiva")
        BigDecimal cantidad,

        Long unidadId,

        String version,

        Boolean activo
) {
}