package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DiscrepanciaInventarioResponse {
    private Long id;
    private Long conteoId;
    private BigDecimal cantidadSistema;
    private Boolean resuelto;
}
