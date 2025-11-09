package com.example.minimal_prod_backend.dto.Request;

import java.math.BigDecimal;

public record ProductoRequest(
        String codigo,
        String nombre,
        Long metodoValoracionId,
        Long tipoId,
        Long unidadBaseId,
        BigDecimal costoBase
) {
}