package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asignaciones")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_trabajo_id")
    private OrdenTrabajo ordenTrabajo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "inicio_planificado", nullable = false)
    private OffsetDateTime inicioPlanificado;

    @Column(name = "fin_planificado", nullable = false)
    private OffsetDateTime finPlanificado;

    @Column(name = "horas_planificadas", precision = 6, scale = 2)
    private BigDecimal horasPlanificadas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignado_por")
    private Usuario asignadoPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_asignacion_id", nullable = false)
    private EstadoAsignacion estadoAsignacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcion_tarea", nullable = false)
    private FuncionTarea funcionTarea;

    @CreationTimestamp
    @Column(name = "creado_en", updatable = false)
    private OffsetDateTime creadoEn;
}
