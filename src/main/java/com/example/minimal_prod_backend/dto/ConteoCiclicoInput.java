package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ConteoCiclicoInput {
    private Long idProducto;
    private Long idBodega;
    private Long idLote;
    private BigDecimal cantidadContada;
    private Long idUnidad;
    private LocalDateTime fecha;
}
