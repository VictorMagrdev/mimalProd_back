package com.example.minimal_prod_backend.dto;

import lombok.Data;

@Data
public class UnidadMedidaInput {
    private String codigo;
    private String nombre;
    private String abreviatura;
    private Long idTipo;
    private Boolean esActiva;
    private Boolean esBase;
}
