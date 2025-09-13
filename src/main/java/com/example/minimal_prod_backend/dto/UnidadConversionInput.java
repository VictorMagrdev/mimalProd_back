package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnidadConversionInput {
    private Long idOrigen;
    private Long idDestino;
    private BigDecimal factor;
}
