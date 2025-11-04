package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record IndicadorProductividadDTO(
        Long ordenId,
        String producto,
        BigDecimal unidadesProducidas,
        BigDecimal horasTrabajadas,
        BigDecimal eficiencia
) {}