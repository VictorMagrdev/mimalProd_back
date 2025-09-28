package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MovimientoInventarioDetalleResponse {
    private Long id;
    private Long movimientoId;
    private Long productoId;
    private Long loteId;
    private BigDecimal cantidad;
    private Long unidadId;
    private BigDecimal costoUnitario;
    private BigDecimal costoTotal;
}
