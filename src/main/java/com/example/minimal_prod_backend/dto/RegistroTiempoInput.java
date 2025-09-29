package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroTiempoInput {
    private Long asignacionId;
    private OffsetDateTime inicioTz;
    private OffsetDateTime finTz;
    private Long tipoActividadId;
    private Long tipoCostoId;
    private Long estadoAprobacionId;
    private Long hojaTimesheetId;
}
