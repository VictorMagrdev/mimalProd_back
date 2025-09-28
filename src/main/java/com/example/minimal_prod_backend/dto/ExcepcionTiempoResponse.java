package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ExcepcionTiempoResponse {
    private Long id;
    private Long usuarioId;
    private String periodo; // Representa tstzrange como String ISO8601
    private String detalles;
    private Boolean resuelto;
    private OffsetDateTime creadoEn;
}
