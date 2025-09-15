package com.example.minimal_prod_backend.dto;

import lombok.Data;

@Data
public class EstacionProduccionInput {
    private String codigo;
    private String nombre;
    private String descripcion;
    private Integer orden;
}
