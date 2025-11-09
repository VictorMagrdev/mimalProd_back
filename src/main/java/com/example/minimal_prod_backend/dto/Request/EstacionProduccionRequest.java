package com.example.minimal_prod_backend.dto.Request;

public record EstacionProduccionRequest(
        String codigo,
        String nombre,
        String descripcion,
        Integer orden
) {
}