package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record OrdenEventoResponse(
        Long id,
        Long ordenId,
        String evento,
        String descripcion,
        OffsetDateTime fecha
) {}