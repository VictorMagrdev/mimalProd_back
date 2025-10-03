package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;
import java.time.Duration;

public record RecursoOperacionResponse(
        Long id,
        Long operacionOrdenId,
        Long usuarioId,
        Long funcionTareaId,
        Duration horasPlanificadas,
        Duration horasReales,
        Long asignadoPor,
        OffsetDateTime creadoEn
) {}