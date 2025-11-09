package com.example.minimal_prod_backend.dto.Request;

import java.time.OffsetDateTime;

public record TipoBodegaRequest(
        String codigo,
        String nombre,
        String descripcion,
        OffsetDateTime creadoEn
) {
}
