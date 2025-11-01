package com.example.minimal_prod_backend.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "discrepancias_inventario")
public class DiscrepanciaInventario {

    @Id
    @Tsid
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conteo_id")
    @ToString.Exclude
    private ConteoCiclico conteo;

    @Column(name = "cantidad_sistema", precision = 18, scale = 6, nullable = false)
    private BigDecimal cantidadSistema;

    @Column(name = "resuelto", nullable = false)
    @Builder.Default
    private Boolean resuelto = false;

}