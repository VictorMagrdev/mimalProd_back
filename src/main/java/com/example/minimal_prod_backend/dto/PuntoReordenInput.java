package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PuntoReordenInput {
    private Long idProducto;
    private BigDecimal stockMinimo;
    private BigDecimal stockSeguridad;
    private Long idUnidad;
}
