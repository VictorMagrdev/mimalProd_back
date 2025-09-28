package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UnidadMedidaResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private String abreviatura;
    private Long unidadMedidaTipoId;
    private Boolean activa;
    private Boolean base;
    private OffsetDateTime creadoEn;
}
