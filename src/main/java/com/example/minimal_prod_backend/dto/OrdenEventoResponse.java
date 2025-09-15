package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdenEventoResponse {
    private Long id;
    private OrdenProduccionResponse orden;
    private String evento;
    private String descripcion;
    private LocalDateTime fecha;
}
