package com.example.minimal_prod_backend.dto;

import lombok.Data;

@Data
public class MovimientoInventarioInput {
    private Long idBodegaOrigen;
    private Long idBodegaDestino;
    private Long idTipoMovimiento;
    private String observaciones;
    private Long creadoPor;
}
