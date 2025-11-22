package com.example.minimal_prod_backend.dto.Response;

import java.time.Duration;
import java.time.OffsetDateTime;

public record IncidenciaResponse(
        Long id,
        String codigo,
        String titulo,
        String descripcion,

        Long tipoIncidenciaId,
        Long estadoId,
        Long maquinaId,
        Long ordenId,
        Long estacionId,
        Long reportadoPorId,
        Long asignadoAId,

        OffsetDateTime fechaCierre,
        Duration tiempoParada,
        OffsetDateTime creadoEn
) {}

