package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrdenTrabajoResponse {
    private Long id;
    private String referencia;
    private Long tipoId;
    private String descripcion;
    private OffsetDateTime inicioPlanificado;
    private OffsetDateTime finPlanificado;
    private Long estadoId;
    private OffsetDateTime creadoEn;
}
