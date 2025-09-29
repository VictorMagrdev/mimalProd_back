package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineaOrdenResponse {
    private Long id;
    private Long ordenId;
    private Integer numeroLinea;
    private Long productoComponenteId;
    private BigDecimal cantidadRequerida;
    private Long unidadComponenteId;
    private BigDecimal cantidadUsada;
    private BigDecimal costoUnitario;
    private BigDecimal costoTotal;
    private String observaciones;
    private OffsetDateTime creadoEn;
}
