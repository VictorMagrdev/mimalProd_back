package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record ExcepcionTiempoResponse(
        Long id,
        Long usuarioId,
        String periodo,
        String detalles,
        OffsetDateTime creadoEn,
        Boolean resuelto
) {
}
