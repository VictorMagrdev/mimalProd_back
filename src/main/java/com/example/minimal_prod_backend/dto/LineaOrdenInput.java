package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineaOrdenInput {
    private Long ordenId;
    private Integer numeroLinea;
    private Long productoComponenteId;
    private BigDecimal cantidadRequerida;
    private Long unidadComponenteId;
    private BigDecimal cantidadUsada;
    private BigDecimal costoUnitario;
    private String observaciones;
}
