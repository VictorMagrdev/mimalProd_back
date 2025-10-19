package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaquinaRequest(
        String codigo,
        String nombre,
        String descripcion,
        String numeroSerie,
        LocalDate fechaCompra,
        BigDecimal costoCompra,
        BigDecimal valorRescate,
        Integer vidaUtilAnios,
        Long centroCostoId,
        Boolean activa
) {}
