package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record UnidadMedidaResponse(
        Long id,
        String codigo,
        String nombre,
        String abreviatura,
        Long unidadMedidaTipoId,
        Boolean activa,
        Boolean base,
        OffsetDateTime creadoEn
) {}