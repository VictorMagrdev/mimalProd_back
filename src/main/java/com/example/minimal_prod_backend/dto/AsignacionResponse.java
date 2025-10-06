package com.example.minimal_prod_backend.dto;

import java.time.Duration;
import java.time.OffsetDateTime;

public record AsignacionResponse(
        Long id,
        Long ordenTrabajoId,
        Long usuarioId,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        Duration horasPlanificadas,
        Long asignadoPor,
        Long estadoAsignacionId,
        Long funcionTareaId,
        OffsetDateTime creadoEn
) {
}
