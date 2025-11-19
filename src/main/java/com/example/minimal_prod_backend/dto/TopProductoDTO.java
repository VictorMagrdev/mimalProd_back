package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record TopProductoDTO(
        long id,
        String nombre,
        BigDecimal totalProducido
) {}
