package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordenes_estacion")
public class OrdenEstacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_id")
    private OrdenProduccion orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estacion_id")
    private EstacionProduccion estacion;

    @Column(name = "inicio_planificado")
    private LocalDateTime inicioPlanificado;

    @Column(name = "fin_planificado")
    private LocalDateTime finPlanificado;

    @Column(name = "inicio_real")
    private LocalDateTime inicioReal;

    @Column(name = "fin_real")
    private LocalDateTime finReal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_orden_estacion_id")
    private EstadoOrdenEstacion estado;

    @Column(length = 150)
    private String observaciones;
}
