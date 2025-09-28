package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RegistroTiempoResponse {
    private Long id;
    private Long asignacionId;
    private OffsetDateTime inicioTz;
    private OffsetDateTime finTz;
    private BigDecimal duracionHoras;
    private Long tipoActividadId;
    private Long tipoCostoId;
    private Long estadoAprobacionId;
    private Long hojaTimesheetId;
    private OffsetDateTime creadoEn;
    private OffsetDateTime actualizadoEn;
}
