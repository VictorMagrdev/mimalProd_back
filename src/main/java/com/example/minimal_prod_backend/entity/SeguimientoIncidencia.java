package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "seguimiento_incidencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeguimientoIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_anterior_id")
    private EstadoIncidencia estadoAnterior;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_nuevo_id")
    private EstadoIncidencia estadoNuevo;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "realizado_en")
    private OffsetDateTime realizadoEn = OffsetDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realizado_por")
    private Usuario realizadoPor;

}
