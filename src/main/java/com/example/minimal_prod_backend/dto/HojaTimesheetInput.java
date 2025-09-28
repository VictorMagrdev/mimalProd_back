package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class HojaTimesheetInput {
    private Long usuarioId;
    private LocalDate inicioPeriodo;
    private LocalDate finPeriodo;
    private Long estadoAprobacionId;
    private Long aprobadoPor;
    private OffsetDateTime aprobadoEn;
}
