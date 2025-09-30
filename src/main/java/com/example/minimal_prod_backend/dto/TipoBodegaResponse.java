package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record TipoBodegaResponse(
        Long id,
        String codigo,
        String nombre,
        String descripcion,
        OffsetDateTime creadoEn
) {}
