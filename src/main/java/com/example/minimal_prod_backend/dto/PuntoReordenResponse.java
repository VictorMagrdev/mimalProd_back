package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record PuntoReordenResponse(
        Long id,
        Long productoId,
        BigDecimal stockMinimo,
        BigDecimal stockSeguridad,
        Long unidadId
) {}