package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaMaterialOrdenResponse {
    private Long id;
    private Long ordenId;
    private Long productoId;
    private Long loteId;
    private BigDecimal cantidadReservada;
    private Long unidadId;
    private LocalDateTime fechaReserva;
}
