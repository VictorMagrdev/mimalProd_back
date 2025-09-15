package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReservaMaterialOrdenResponse {
    private Long id;
    private OrdenProduccionResponse orden;
    private ProductoResponse producto;
    private LoteProduccionResponse lote;
    private BigDecimal cantidadReservada;
    private UnidadMedidaResponse unidad;
    private LocalDateTime fechaReserva;
}
