package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimientoInventarioResponse {
    private Long id;
    private LocalDateTime fecha;
    private BodegaResponse bodegaOrigen;
    private BodegaResponse bodegaDestino;
    private TipoMovimientoResponse tipoMovimiento;
    private String observaciones;
    private Long creadoPor;
    private LocalDateTime creadoEn;
}
