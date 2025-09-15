package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReservaMaterialOrdenInput {
    private Long idOrden;
    private Long idProducto;
    private Long idLote;
    private BigDecimal cantidadReservada;
    private Long idUnidad;
    private LocalDateTime fechaReserva;
}
