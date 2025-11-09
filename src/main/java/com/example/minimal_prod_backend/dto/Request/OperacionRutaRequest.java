package com.example.minimal_prod_backend.dto.Request;

import java.time.Duration;

public record OperacionRutaRequest(
        Long rutaId,
        Long estacionId,
        Integer secuencia,
        String nombre,
        Duration tiempoSetup,
        Duration tiempoEjecucion,
        Duration tiempoCola
) {
}
