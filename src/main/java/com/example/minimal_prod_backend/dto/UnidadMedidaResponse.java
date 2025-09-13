package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UnidadMedidaResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private String abreviatura;
    private UnidadMedidaTipoResponse tipo;
    private Boolean esActiva;
    private Boolean esBase;
    private LocalDateTime creadoEn;
}
