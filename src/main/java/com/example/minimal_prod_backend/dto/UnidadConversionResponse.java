package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnidadConversionResponse {
    private Integer id;
    private UnidadMedidaResponse origen;
    private UnidadMedidaResponse destino;
    private BigDecimal factor;
}
