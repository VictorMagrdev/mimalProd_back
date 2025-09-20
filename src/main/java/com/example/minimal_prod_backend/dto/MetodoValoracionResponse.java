package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoValoracionResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
}
