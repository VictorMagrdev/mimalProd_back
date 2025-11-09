package com.example.minimal_prod_backend.dto.Response;

import java.math.BigDecimal;

public record DiscrepanciaInventarioResponse(
        Long id,
        Long conteoId,
        BigDecimal cantidadSistema,
        Boolean resuelto
) {
}