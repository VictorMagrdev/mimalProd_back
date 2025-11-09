package com.example.minimal_prod_backend.dto.Request;

import java.time.Duration;

public record RecursoOperacionRequest(
        Long operacionOrdenId,
        Long usuarioId,
        Long funcionTareaId,
        Duration horasPlanificadas,
        Duration horasReales,
        Long asignadoPor
) {
}