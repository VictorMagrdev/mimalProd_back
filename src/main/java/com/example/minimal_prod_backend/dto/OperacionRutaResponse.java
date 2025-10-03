package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;
import java.time.Duration;

public record OperacionRutaResponse(
        Long id,
        Long rutaId,
        Long estacionId,
        Integer secuencia,
        String nombre,
        Duration tiempoSetup,
        Duration tiempoEjecucion,
        Duration tiempoCola,
        OffsetDateTime creadoEn
) {}