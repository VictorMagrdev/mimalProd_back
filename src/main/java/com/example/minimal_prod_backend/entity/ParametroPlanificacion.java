package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parametros_planificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParametroPlanificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", unique = true)
    private Producto producto;

    @Column(name = "lead_time_dias")
    @Builder.Default
    private Integer leadTimeDias = 0;

    @Column(name = "lote_minimo", precision = 18, scale = 6)
    @Builder.Default
    private Double loteMinimo = 0.0;

    @Column(name = "lote_economico", precision = 18, scale = 6)
    @Builder.Default
    private Double loteEconomico = 0.0;

    @Column(name = "politica_inventario", length = 50)
    @Builder.Default
    private String politicaInventario = "MRP";

    @Column(name = "actualizado_en")
    @Builder.Default
    private LocalDateTime actualizadoEn = LocalDateTime.now();
}