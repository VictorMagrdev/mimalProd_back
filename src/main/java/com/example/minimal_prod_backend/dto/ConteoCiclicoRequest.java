package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ConteoCiclicoRequest(
        Long productoId,
        Long bodegaId,
        Long loteId,
        BigDecimal cantidadContada,
        Long unidadId,
        OffsetDateTime fecha
) {
}