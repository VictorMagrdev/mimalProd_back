package com.example.minimal_prod_backend.dto;

import java.time.Duration;
import java.time.OffsetDateTime;

public record IncidenciaRequest(
        String codigo,
        String titulo,
        String descripcion,
        Long tipoIncidenciaId,
        Long estadoId,
        OffsetDateTime fechaCierre,
        Duration tiempoParada
) {}
