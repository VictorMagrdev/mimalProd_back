package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record LoteProduccionRequest(
        String numeroLote,
        Long productoId,
        OffsetDateTime fabricadoEn,
        OffsetDateTime venceEn
) {
}