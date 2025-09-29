package com.example.minimal_prod_backend.dto;

public record ExcepcionTiempoRequest(
        Long usuarioId,
        String periodo,
        String detalles
) {}