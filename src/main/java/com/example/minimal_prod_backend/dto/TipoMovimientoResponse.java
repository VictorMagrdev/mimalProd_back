package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TipoMovimientoResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean afectaWip;
    private LocalDateTime creadoEn;
}
