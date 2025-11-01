package com.example.minimal_prod_backend.dto;

import jakarta.validation.constraints.NotNull;

public record BodegaRequest(
        String codigo,
        String nombre,
        String descripcion,
        @NotNull Long tipoBodegaId
) {
}
