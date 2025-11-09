package com.example.minimal_prod_backend.dto.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EstructuraProductoResponse(
        Long id,
        Long productoPadreId,
        String productoPadreCodigo,
        String productoPadreNombre,
        Long productoHijoId,
        String productoHijoCodigo,
        String productoHijoNombre,
        BigDecimal cantidad,
        Long unidadId,
        String unidadNombre,
        String version,
        Boolean activo,
        LocalDateTime creadoEn
) {
}
