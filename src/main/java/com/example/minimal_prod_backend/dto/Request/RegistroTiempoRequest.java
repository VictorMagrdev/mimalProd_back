package com.example.minimal_prod_backend.dto.Request;

import java.time.OffsetDateTime;

public record RegistroTiempoRequest(
        Long asignacionId,
        OffsetDateTime inicioTz,
        OffsetDateTime finTz,
        Long tipoActividadId,
        Long tipoCostoId,
        Long estadoAprobacionId,
        Long hojaTimesheetId
) {
}