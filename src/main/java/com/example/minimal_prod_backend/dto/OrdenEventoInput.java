package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrdenEventoInput {
    private Long ordenId;
    private String evento;
    private String descripcion;
    private OffsetDateTime fecha;
}
