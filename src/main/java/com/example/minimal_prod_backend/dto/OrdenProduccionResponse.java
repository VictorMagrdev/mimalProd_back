package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdenProduccionResponse {
    private Integer id;
    private String numeroOrden;
    private LoteProduccionResponse lote;
    private ProductoResponse producto;
    private BigDecimal cantidad;
    private UnidadMedidaResponse unidad;
    private EstadoOrdenResponse estado;
    private LocalDateTime inicioPlanificado;
    private LocalDateTime finPlanificado;
    private LocalDateTime inicioReal;
    private LocalDateTime finReal;
    private BigDecimal cantidadDesperdicio;
    private BigDecimal cantidadProducida;
    private Integer creadoPor;
    private String observaciones;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
}
