package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoInput {
    private String codigo;
    private String nombre;
    private Integer idUnidadBase;
    private BigDecimal costoBase;
}
