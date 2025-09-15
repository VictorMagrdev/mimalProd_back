package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdenEstacionInput {
    private Long idOrden;
    private Long idEstacion;
    private LocalDateTime inicioPlanificado;
    private LocalDateTime finPlanificado;
    private LocalDateTime inicioReal;
    private LocalDateTime finReal;
    private String estado;
    private String observaciones;
}
