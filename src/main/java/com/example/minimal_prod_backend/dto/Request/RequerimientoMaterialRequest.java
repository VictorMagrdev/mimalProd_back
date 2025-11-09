package com.example.minimal_prod_backend.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RequerimientoMaterialRequest(
        @NotNull(message = "El producto es requerido")
        Long productoId,

        Long ordenProduccionId,

        @NotNull(message = "La cantidad requerida es obligatoria")
        @Positive(message = "La cantidad requerida debe ser positiva")
        BigDecimal cantidadRequerida,

        BigDecimal cantidadDisponible,

        LocalDate fechaNecesidad
) {
}
