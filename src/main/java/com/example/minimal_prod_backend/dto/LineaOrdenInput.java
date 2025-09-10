package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LineaOrdenInput {
    private Integer idOrden;
    private Integer numeroLinea;
    private Integer idProductoComponente;
    private BigDecimal cantidadRequerida;
    private Integer idUnidadComponente;
    private BigDecimal cantidadUsada;
    private BigDecimal costoUnitario;
    private String observaciones;
}
