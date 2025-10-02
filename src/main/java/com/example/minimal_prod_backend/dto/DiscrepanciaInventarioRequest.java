package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;

public record DiscrepanciaInventarioRequest(
        Long conteoId,
        BigDecimal cantidadSistema,
        Boolean resuelto
) {}