package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ConteoCiclicoResponse {
    private Long id;
    private ProductoResponse producto;
    private BodegaResponse bodega;
    private LoteProduccionResponse lote;
    private BigDecimal cantidadContada;
    private UnidadMedidaResponse unidad;
    private LocalDateTime fecha;
}
