package com.example.minimal_prod_backend.dto.Response;

import java.time.OffsetDateTime;

public record TipoMovimientoResponse(
        Long id,
        String codigo,
        String nombre,
        String descripcion,
        Boolean afectaWip,
        OffsetDateTime creadoEn
) {
}
