package com.example.minimal_prod_backend.dto.Response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record InventarioLoteResponse(
        Long id,
        Long productoId,
        Long loteId,
        Long bodegaId,
        BigDecimal cantidad,
        Long unidadId,
        OffsetDateTime actualizadoEn
) {
}