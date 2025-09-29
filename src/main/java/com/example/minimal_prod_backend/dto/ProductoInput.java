package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoInput {
    private String codigo;
    private String nombre;
    private Long metodoValoracionId;
    private Long tipoId;
    private Long unidadBaseId;
    private BigDecimal costoBase;
}
