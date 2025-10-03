package com.example.minimal_prod_backend.dto;

import java.time.Duration;
import java.time.OffsetDateTime;

public record RecursoOperacionResponse(
        Long id,
        Long operacionOrdenId,
        Long usuarioId,
        Long funcionTareaId,
        Duration horasPlanificadas,
        Duration horasReales,
        Long asignadoPor,
        OffsetDateTime creadoEn
) {
}