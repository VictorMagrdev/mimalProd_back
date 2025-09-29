package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record TarifaEmpleadoResponse(
        Long id,
        Long usuarioId,
        BigDecimal tarifa,
        String moneda,
        LocalDate vigenteDesde,
        LocalDate vigenteHasta,
        OffsetDateTime creadoEn
) {}
