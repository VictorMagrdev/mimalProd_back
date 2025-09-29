package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodegaResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Long tipoBodegaId;
    private OffsetDateTime creadoEn;
}
