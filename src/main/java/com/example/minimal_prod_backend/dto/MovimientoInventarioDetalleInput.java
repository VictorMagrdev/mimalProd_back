package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimientoInventarioDetalleInput {
    private Long idMovimiento;
    private Long idProducto;
    private Long idLote;
    private BigDecimal cantidad;
    private Long idUnidad;
    private BigDecimal costoUnitario;
}
