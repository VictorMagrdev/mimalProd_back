package com.example.minimal_prod_backend.dto.Request;

import java.math.BigDecimal;

public record InventarioLoteRequest(
        Long productoId,
        Long loteId,
        Long bodegaId,
        BigDecimal cantidad,
        Long unidadId
) {
}