package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record AsignacionRequest(
        Long ordenTrabajoId,
        Long usuarioId,
        OffsetDateTime inicioPlanificado,
        OffsetDateTime finPlanificado,
        BigDecimal horasPlanificadas,
        Long asignadoPor,
        Long estadoAsignacionId,
        Long funcionTareaId
) {}