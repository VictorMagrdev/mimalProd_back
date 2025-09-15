package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstacionProduccionResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Integer orden;
    private LocalDateTime creadoEn;
}
