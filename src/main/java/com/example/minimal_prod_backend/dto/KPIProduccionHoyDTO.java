package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record KPIProduccionHoyDTO(
        long ordenesEnProceso,
        long ordenesAtrasadas,
        BigDecimal produccionHoy,
        BigDecimal cumplimientoHoyPct
) {}
