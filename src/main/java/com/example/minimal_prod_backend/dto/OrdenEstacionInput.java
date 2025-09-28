package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrdenEstacionInput {
    private Long ordenId;
    private Long estacionId;
    private OffsetDateTime inicioPlanificado;
    private OffsetDateTime finPlanificado;
    private OffsetDateTime inicioReal;
    private OffsetDateTime finReal;
    private Long estadoOrdenEstacionId;
    private String observaciones;
}
