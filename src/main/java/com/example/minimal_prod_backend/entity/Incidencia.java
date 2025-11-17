package com.example.minimal_prod_backend.entity;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.Duration;
import java.time.OffsetDateTime;

@Entity
@Table(name = "incidencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String codigo;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_incidencia_id", nullable = false)
    private TipoIncidencia tipoIncidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoIncidencia estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maquina_id", nullable = false)
    private Maquina maquina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_id", nullable = false)
    private OrdenProduccion orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estacion_id", nullable = false)
    private EstacionProduccion estacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportado_por", nullable = false)
    private Usuario reportadoPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignado_a", nullable = false)
    private Usuario asignadoA;

    @Column(name = "fecha_cierre")
    private OffsetDateTime fechaCierre;

    @Type(PostgreSQLIntervalType.class)
    @Column(name = "tiempo_parada")
    private Duration tiempoParada;

    @CreationTimestamp
    @Column(name = "creado_en")
    private OffsetDateTime creadoEn;
}