package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record OrdenEventoRequest(
        Long ordenId,
        String evento,
        String descripcion,
        OffsetDateTime fecha
) {}