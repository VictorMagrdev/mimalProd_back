package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InventarioLoteResponse {
    private Long id;
    private Long productoId;
    private Long loteId;
    private Long bodegaId;
    private BigDecimal cantidad;
    private Long unidadId;
    private LocalDateTime actualizadoEn;
}
