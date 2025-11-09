package com.example.minimal_prod_backend.dto.Request;

import java.time.Duration;
import java.time.OffsetDateTime;

public record AsignacionRequest(
        Long ordenTrabajoId,
        Long usuarioId,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        Duration horasPlanificadas,
        Long asignadoPor,
        Long estadoAsignacionId,
        Long funcionTareaId
) {
}