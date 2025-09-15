package com.example.minimal_prod_backend.dto;

import lombok.Data;

@Data
public class BodegaInput {
    private String codigo;
    private String nombre;
    private String descripcion;
    private Long idTipo;
}
