package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record ReporteCostoMaterialDTO(
        String material,
        BigDecimal cantidad,
        BigDecimal costoUnitario,
        BigDecimal costoTotal
) {}
