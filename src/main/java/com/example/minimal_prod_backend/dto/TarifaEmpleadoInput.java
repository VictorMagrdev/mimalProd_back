package com.example.minimal_prod_backend.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TarifaEmpleadoInput {
    private Long usuarioId;
    private BigDecimal tarifa;
    private String moneda; // Ej: "COP"
    private LocalDate vigenteDesde;
    private LocalDate vigenteHasta;
}

