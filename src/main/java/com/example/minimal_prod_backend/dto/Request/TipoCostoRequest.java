package com.example.minimal_prod_backend.dto.Request;

import java.time.OffsetDateTime;

public record TipoCostoRequest(
        String codigo,
        String nombre,
        String descripcion,
        Boolean activo,
        OffsetDateTime creadoEn
) {
}