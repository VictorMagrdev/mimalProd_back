package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoInventarioResponse {
    private Long id;
    private OffsetDateTime fecha;
    private Long bodegaOrigenId;
    private Long bodegaDestinoId;
    private Long tipoMovimientoId;
    private String referencia;
    private String observaciones;
    private Long creadoPor;
    private OffsetDateTime creadoEn;
    List<MovimientoInventarioDetalleInput> detalles;
}
