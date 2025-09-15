package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventarioLoteInput {
    private Long idProducto;
    private Long idLote;
    private Long idBodega;
    private BigDecimal cantidad;
    private Long idUnidad;
}
