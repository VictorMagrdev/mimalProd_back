package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnidadConversionResponse {
    private Long id;
    private UnidadMedidaResponse origen;
    private UnidadMedidaResponse destino;
    private BigDecimal factor;
}
