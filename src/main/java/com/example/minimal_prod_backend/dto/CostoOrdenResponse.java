package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CostoOrdenResponse {
    private Long id;
    private OrdenProduccionResponse orden;
    private TipoCostoResponse tipoCosto;
    private String descripcion;
    private BigDecimal monto;
    private String moneda;
    private LocalDateTime registradoEn;
}
