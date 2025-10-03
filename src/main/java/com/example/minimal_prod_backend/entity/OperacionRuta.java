package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "tiempo_setup")
    private Duration tiempoSetup = Duration.ZERO;

    @Column(name = "tiempo_ejecucion")
    private Duration tiempoEjecucion = Duration.ZERO;

    @Column(name = "tiempo_cola")
    private Duration tiempoCola = Duration.ZERO;

    @Column(name = "creado_en", updatable = false, insertable = false)
    private OffsetDateTime creadoEn;
}

