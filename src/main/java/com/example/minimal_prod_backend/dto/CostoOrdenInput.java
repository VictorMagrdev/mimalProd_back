package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CostoOrdenInput {
    private Long idOrden;
    private Long idTipoCosto;
    private String descripcion;
    private BigDecimal monto;
    private String moneda;
}
