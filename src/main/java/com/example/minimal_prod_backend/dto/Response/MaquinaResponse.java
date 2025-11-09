package com.example.minimal_prod_backend.dto.Response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record MaquinaResponse(
        Long id,
        String codigo,
        String nombre,
        String descripcion,
        String numeroSerie,
        LocalDate fechaCompra,
        BigDecimal costoCompra,
        BigDecimal valorRescate,
        Integer vidaUtilAnios,
        Long centroCostoId,
        Boolean activa,
        OffsetDateTime creadoEn
) {
}
