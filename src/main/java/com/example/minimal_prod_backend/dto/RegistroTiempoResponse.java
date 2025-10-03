package com.example.minimal_prod_backend.dto;

import java.time.OffsetDateTime;

public record RegistroTiempoResponse(
        Long id,
        Long asignacionId,
        OffsetDateTime inicioTz,
        OffsetDateTime finTz,
        String duracion,
        Long tipoActividadId,
        Long tipoCostoId,
        Long estadoAprobacionId,
        Long hojaTimesheetId,
        OffsetDateTime creadoEn,
        OffsetDateTime actualizadoEn
) {
}