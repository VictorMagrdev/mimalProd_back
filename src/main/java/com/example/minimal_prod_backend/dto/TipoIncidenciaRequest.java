package com.example.minimal_prod_backend.dto;


import com.example.minimal_prod_backend.entity.TipoPrioridad;

public record TipoIncidenciaRequest(String codigo, String nombre, String descripcion, TipoPrioridad prioridadBase) {
}

