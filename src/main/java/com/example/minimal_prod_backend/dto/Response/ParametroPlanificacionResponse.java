package com.example.minimal_prod_backend.dto.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParametroPlanificacionResponse(
        Long id,
        Long productoId,
        String productoCodigo,
        String productoNombre,
        Integer leadTimeDias,
        BigDecimal loteMinimo,
        BigDecimal loteEconomico,
        String politicaInventario,
        LocalDateTime actualizadoEn
) {
}
