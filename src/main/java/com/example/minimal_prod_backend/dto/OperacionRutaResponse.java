package com.example.minimal_prod_backend.dto;

import java.time.Duration;
import java.time.OffsetDateTime;

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
) {
}