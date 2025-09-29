package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarifaEmpleadoInput {
    private Long usuarioId;
    private BigDecimal tarifa;
    private String moneda; // Ej: "COP"
    private LocalDate vigenteDesde;
    private LocalDate vigenteHasta;
}

