package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdenProduccionInput {
    private String numeroOrden;
    private Integer idLote;
    private Long idProducto;
    private BigDecimal cantidad;
    private Integer idUnidad;
    private Integer idEstado;
    private LocalDateTime inicioPlanificado;
    private LocalDateTime finPlanificado;
    private LocalDateTime inicioReal;
    private LocalDateTime finReal;
    private BigDecimal cantidadDesperdicio;
    private BigDecimal cantidadProducida;
    private Integer creadoPor;
    private String observaciones;
}
