package com.example.minimal_prod_backend.dto;

public record EstacionProduccionRequest(
        String codigo,
        String nombre,
        String descripcion,
        Integer orden
) {
}