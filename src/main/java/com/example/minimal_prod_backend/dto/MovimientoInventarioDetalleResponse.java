package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimientoInventarioDetalleResponse {
    private Long id;
    private Long idMovimiento;
    private ProductoResponse producto;
    private LoteProduccionResponse lote;
    private BigDecimal cantidad;
    private UnidadMedidaResponse unidad;
    private BigDecimal costoUnitario;
    private BigDecimal costoTotal;
}
