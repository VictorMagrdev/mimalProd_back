package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record MovimientoInventarioInput(
        OffsetDateTime fecha,
        Long bodegaOrigenId,
        Long bodegaDestinoId,
        Long tipoMovimientoId,
        String referencia,
        String observaciones,
        Long creadoPor,
        OffsetDateTime creadoEn,
        List<MovimientoInventarioDetalleInput> detalles
) {}
