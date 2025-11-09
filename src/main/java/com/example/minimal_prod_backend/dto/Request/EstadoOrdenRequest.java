package com.example.minimal_prod_backend.dto.Request;

public record EstadoOrdenRequest(
        String codigo,
        String nombre,
        String descripcion,
        Boolean activo
) {
}