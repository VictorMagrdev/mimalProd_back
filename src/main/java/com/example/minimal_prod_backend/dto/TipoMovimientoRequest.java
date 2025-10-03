package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record TipoMovimientoRequest(
        String codigo,
        String nombre,
        String descripcion,
        Boolean afectaWip,
        OffsetDateTime creadoEn

) {
}
