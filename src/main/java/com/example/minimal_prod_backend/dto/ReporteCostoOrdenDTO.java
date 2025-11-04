package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record ReporteCostoOrdenDTO(
        Long ordenId,
        String producto,
        BigDecimal costoMaterial,
        BigDecimal costoManoObra,
        BigDecimal costoIndirecto,
        BigDecimal total
) {}
