package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TarifaEmpleadoResponse {
    private Long id;
    private Long usuarioId;
    private BigDecimal tarifa;
    private String moneda;
    private LocalDate vigenteDesde;
    private LocalDate vigenteHasta;
    private OffsetDateTime creadoEn;
}
