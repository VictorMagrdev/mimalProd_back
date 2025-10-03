package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ProductoResponse(
        Long id,
        String codigo,
        String nombre,
        Long metodoValoracionId,
        Long tipoId,
        Long unidadBaseId,
        BigDecimal costoBase,
        OffsetDateTime creadoEn
) {
}