package com.example.minimal_prod_backend.dto;

public record UnidadMedidaRequest(
        String codigo,
        String nombre,
        String abreviatura,
        Long unidadMedidaTipoId,
        Boolean activa,
        Boolean base
) {}