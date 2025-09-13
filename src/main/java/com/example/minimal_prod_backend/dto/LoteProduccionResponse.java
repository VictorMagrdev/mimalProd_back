package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoteProduccionResponse {
    private Long id;
    private String numeroLote;
    private ProductoResponse producto;
    private LocalDateTime fabricadoEn;
    private LocalDateTime venceEn;
    private LocalDateTime creadoEn;
}
