package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LineaOrdenResponse {
    private Long id;
    private OrdenProduccionResponse orden;
    private Integer numeroLinea;
    private ProductoResponse productoComponente;
    private BigDecimal cantidadRequerida;
    private UnidadMedidaResponse unidadComponente;
    private BigDecimal cantidadUsada;
    private BigDecimal costoUnitario;
    private BigDecimal costoTotal;
    private String observaciones;
    private LocalDateTime creadoEn;
}
