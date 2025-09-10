package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnidadConversionInput {
    private Integer idOrigen;
    private Integer idDestino;
    private BigDecimal factor;
}
