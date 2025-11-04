package com.example.minimal_prod_backend.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;

import java.time.Duration;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asignaciones")
public class Asignacion {

    @Id
    @Tsid
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_trabajo_id")
    @ToString.Exclude
    private OrdenTrabajo ordenTrabajo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @ToString.Exclude
    private Usuario usuario;

    @Column(name = "inicio_planificado", nullable = false)
    private OffsetDateTime inicioPlanificado;

    @Column(name = "fin_planificado", nullable = false)
    private OffsetDateTime finPlanificado;

    @Column(name = "horas_planificadas", columnDefinition = "interval")
    private Duration horasPlanificadas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignado_por")
    @ToString.Exclude
    private Usuario asignadoPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_asignacion_id", nullable = false)
    @ToString.Exclude
    private EstadoAsignacion estadoAsignacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcion_tarea", nullable = false)
    @ToString.Exclude
    private FuncionTarea funcionTarea;

    @CreationTimestamp
    @Immutable
    @Column(name = "creado_en", updatable = false)
    private OffsetDateTime creadoEn;

}