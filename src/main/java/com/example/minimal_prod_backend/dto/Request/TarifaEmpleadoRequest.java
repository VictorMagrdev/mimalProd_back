package com.example.minimal_prod_backend.dto.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TarifaEmpleadoRequest(
        Long usuarioId,
        BigDecimal tarifa,
        String moneda,
        LocalDate vigenteDesde,
        LocalDate vigenteHasta
) {
}
