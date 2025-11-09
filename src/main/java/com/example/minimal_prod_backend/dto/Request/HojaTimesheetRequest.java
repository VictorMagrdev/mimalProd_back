package com.example.minimal_prod_backend.dto.Request;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record HojaTimesheetRequest(
        Long usuarioId,
        LocalDate inicioPeriodo,
        LocalDate finPeriodo,
        Long estadoAprobacionId,
        Long aprobadoPor,
        OffsetDateTime aprobadoEn,
        OffsetDateTime creadoEn
) {
}