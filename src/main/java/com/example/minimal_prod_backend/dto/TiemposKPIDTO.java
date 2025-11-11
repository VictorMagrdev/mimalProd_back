package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record TiemposKPIDTO(
        BigDecimal horasPlanificadas,
        BigDecimal horasReales,
        BigDecimal cumplimiento
) {}
