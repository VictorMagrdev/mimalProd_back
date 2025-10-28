package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record RequerimientoMaterialResponse(
        Long id,
        Long productoId,
        String productoCodigo,
        String productoNombre,
        Long ordenProduccionId,
        String ordenProduccionCodigo,
        BigDecimal cantidadRequerida,
        BigDecimal cantidadDisponible,
        BigDecimal cantidadAPedir,
        LocalDate fechaNecesidad,
        LocalDateTime creadoEn
) {}
