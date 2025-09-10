package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductoResponse {
    private Integer id;
    private String codigo;
    private String nombre;
    private UnidadMedidaResponse unidadBase;
    private BigDecimal costoBase;
    private LocalDateTime creadoEn;
}
