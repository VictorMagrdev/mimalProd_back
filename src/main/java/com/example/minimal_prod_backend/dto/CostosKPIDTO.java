package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record CostosKPIDTO(
        BigDecimal totalCostos,
        BigDecimal costoPromedioOrden,
        BigDecimal materiales,
        BigDecimal manoObra,
        BigDecimal indirectos
) {}
