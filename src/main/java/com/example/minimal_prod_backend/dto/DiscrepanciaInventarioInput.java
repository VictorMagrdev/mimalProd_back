package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DiscrepanciaInventarioInput {
    private Long idConteo;
    private BigDecimal cantidadSistema;
    private BigDecimal cantidadContada;
    private Boolean resuelto;
}
