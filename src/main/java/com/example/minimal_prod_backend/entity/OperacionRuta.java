package com.example.minimal_prod_backend.entity;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.Duration;
import java.time.OffsetDateTime;

@Entity
@Table(name = "operaciones_ruta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperacionRuta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id", nullable = false)
    private RutaProduccion ruta;

    @Column(name = "estacion_id")
    private Long estacionId;

    @Column(nullable = false)
    private Integer secuencia;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Builder.Default
    @Type(PostgreSQLIntervalType.class)
    @Column(name = "tiempo_setup", columnDefinition = "interval")
    private Duration tiempoSetup = Duration.ZERO;

    @Builder.Default
    @Type(PostgreSQLIntervalType.class)
    @Column(name = "tiempo_ejecucion", columnDefinition = "interval")
    private Duration tiempoEjecucion = Duration.ZERO;

    @Builder.Default
    @Type(PostgreSQLIntervalType.class)
    @Column(name = "tiempo_cola", columnDefinition = "interval")
    private Duration tiempoCola = Duration.ZERO;

    @Column(name = "creado_en", updatable = false, insertable = false)
    private OffsetDateTime creadoEn;
}

