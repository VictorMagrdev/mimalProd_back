package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.OffsetDateTime;

@Entity
@Table(name = "recursos_operacion")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class RecursoOperacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operacion_orden_id", nullable = false)
    private OperacionOrden operacionOrden;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "funcion_tarea_id")
    private Long funcionTareaId;

    @Column(name = "horas_planificadas")
    private Duration horasPlanificadas;

    @Column(name = "horas_reales")
    private Duration horasReales;

    @Column(name = "asignado_por")
    private Long asignadoPor;

    @Column(name = "creado_en", updatable = false, insertable = false)
    private OffsetDateTime creadoEn;
}
