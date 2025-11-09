package com.example.minimal_prod_backend.dto.Response;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record HojaTimesheetResponse(
        Long id,
        Long usuarioId,
        LocalDate inicioPeriodo,
        LocalDate finPeriodo,
        Long estadoAprobacionId,
        Long aprobadoPor,
        OffsetDateTime aprobadoEn,
        OffsetDateTime creadoEn
) {
}
