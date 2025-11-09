package com.example.minimal_prod_backend.dto.Response;

import java.time.OffsetDateTime;

public record EstacionProduccionResponse(
        Long id,
        String codigo,
        String nombre,
        String descripcion,
        Integer orden,
        OffsetDateTime creadoEn
) {
}