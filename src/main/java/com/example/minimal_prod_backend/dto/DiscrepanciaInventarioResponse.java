package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DiscrepanciaInventarioResponse {
    private Long id;
    private ConteoCiclicoResponse conteo;
    private BigDecimal cantidadSistema;
    private BigDecimal cantidadContada;
    private BigDecimal diferencia;
    private Boolean resuelto;
}
