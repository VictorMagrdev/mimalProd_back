package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "operaciones_orden")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class OperacionOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orden_id")
    private Long ordenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operacion_ruta_id")
    private OperacionRuta operacionRuta;

    @Column(name = "estacion_id")
    private Long estacionId;

    @Column(nullable = false)
    private Integer secuencia;

    @Column(name = "inicio_planificado")
    private OffsetDateTime inicioPlanificado;

    @Column(name = "fin_planificado")
    private OffsetDateTime finPlanificado;

    @Column(name = "inicio_real")
    private OffsetDateTime inicioReal;

    @Column(name = "fin_real")
    private OffsetDateTime finReal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private OperacionOrdenEstado estado;

    @Column(length = 150)
    private String observaciones;
}
