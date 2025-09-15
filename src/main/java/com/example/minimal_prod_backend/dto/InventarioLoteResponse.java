package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InventarioLoteResponse {
    private Long id;
    private ProductoResponse producto;
    private LoteProduccionResponse lote;
    private BodegaResponse bodega;
    private BigDecimal cantidad;
    private UnidadMedidaResponse unidad;
    private LocalDateTime actualizadoEn;
}
