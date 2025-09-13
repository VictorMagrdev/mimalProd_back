package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstadoOrdenResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private LocalDateTime creadoEn;
}
