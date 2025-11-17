package com.example.minimal_prod_backend.dto.Request;

import java.time.OffsetDateTime;
import java.util.List;

public record MovimientoInventarioRequest(
        OffsetDateTime fecha,
        Long bodegaOrigenId,
        Long bodegaDestinoId,
        Long tipoMovimientoId,
        String referencia,
        String observaciones,
        List<MovimientoInventarioDetalleRequest> detalles
) {
}
