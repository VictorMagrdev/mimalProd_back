package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenProduccionInput {
    private String numeroOrden;
    private BigDecimal cantidad;
    private Long unidadId;
    private Long estadoId;
    private OffsetDateTime inicioPlanificado;
    private OffsetDateTime finPlanificado;
    private OffsetDateTime inicioReal;
    private OffsetDateTime finReal;
    private BigDecimal cantidadDesperdicio;
    private BigDecimal cantidadProducida;
    private Long creadoPor;
    private String observaciones;
}
