package com.example.minimal_prod_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoteProduccionInput {
    private String numeroLote;
    private Integer idProducto;
    private LocalDateTime fabricadoEn;
    private LocalDateTime venceEn;
}
