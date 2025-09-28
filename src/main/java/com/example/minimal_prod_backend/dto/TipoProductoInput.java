package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TipoProductoInput {
    private String codigo;
    private String nombre;
    private String descripcion;
}
