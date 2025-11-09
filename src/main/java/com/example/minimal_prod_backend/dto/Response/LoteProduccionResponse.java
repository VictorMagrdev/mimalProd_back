package com.example.minimal_prod_backend.dto.Response;

import java.time.OffsetDateTime;

public record LoteProduccionResponse(
        Long id,
        String numeroLote,
        Long productoId,
        OffsetDateTime fabricadoEn,
        OffsetDateTime venceEn,
        OffsetDateTime creadoEn
) {
}