package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PuntoReordenResponse {
    private Long id;
    private ProductoResponse producto;
    private BigDecimal stockMinimo;
    private BigDecimal stockSeguridad;
    private UnidadMedidaResponse unidad;
}
