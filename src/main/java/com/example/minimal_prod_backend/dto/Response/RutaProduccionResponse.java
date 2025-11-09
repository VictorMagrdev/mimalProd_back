package com.example.minimal_prod_backend.dto.Response;

import java.time.OffsetDateTime;

public record RutaProduccionResponse(
        Long id,
        Long productoId,
        String version,
        String nombre,
        Boolean activo,
        OffsetDateTime creadoEn
) {
}