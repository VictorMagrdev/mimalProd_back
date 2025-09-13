package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LineaOrdenInput {
    private Long idOrden;
    private Integer numeroLinea;
    private Long idProductoComponente;
    private BigDecimal cantidadRequerida;
    private Long idUnidadComponente;
    private BigDecimal cantidadUsada;
    private BigDecimal costoUnitario;
    private String observaciones;
}
