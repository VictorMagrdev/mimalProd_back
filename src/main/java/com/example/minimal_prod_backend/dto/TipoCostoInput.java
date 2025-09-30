package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

// ========================= TIPO COSTO =========================
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoCostoInput {
    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private OffsetDateTime creadoEn;
}
