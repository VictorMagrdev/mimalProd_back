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
public class PuntoReordenResponse {
    private Long id;
    private Long productoId;
    private BigDecimal stockMinimo;
    private BigDecimal stockSeguridad;
    private Long unidadId;
}
