package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CostoOrdenResponse {
    private Long id;
    private Long ordenId;
    private Long tipoCostoId;
    private String descripcion;
    private BigDecimal monto;
    private String moneda;
    private OffsetDateTime registradoEn;
}
