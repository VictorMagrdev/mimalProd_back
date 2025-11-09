package com.example.minimal_prod_backend.dto.Response;

import java.time.OffsetDateTime;

public record BodegaResponse(
        Long id,
        String codigo,
        String nombre,
        String descripcion,
        Long tipoBodegaId,
        OffsetDateTime creadoEn
) {
}