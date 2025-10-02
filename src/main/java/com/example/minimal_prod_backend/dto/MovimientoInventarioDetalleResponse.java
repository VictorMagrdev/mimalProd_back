package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record MovimientoInventarioDetalleResponse(
        Long id,
        Long movimientoId,
        Long productoId,
        Long loteId,
        BigDecimal cantidad,
        Long unidadId,
        BigDecimal costoUnitario,
        BigDecimal costoTotal
) {}