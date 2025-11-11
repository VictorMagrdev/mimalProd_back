package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record ProduccionKPIDTO(
        long ordenesFinalizadas,
        BigDecimal eficiencia,
        BigDecimal desperdicio,
        BigDecimal horasPromedio
) {}



