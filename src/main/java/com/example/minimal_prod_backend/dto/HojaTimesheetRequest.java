package com.example.minimal_prod_backend.dto;

import java.time.LocalDate;

public record HojaTimesheetRequest(
        Long usuarioId,
        LocalDate inicioPeriodo,
        LocalDate finPeriodo,
        Long estadoAprobacionId,
        Long aprobadoPor
) {}