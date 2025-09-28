package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discrepancias_inventario")
public class DiscrepanciaInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conteo_id")
    private ConteoCiclico conteo;

    @Column(name = "cantidad_sistema", precision = 18, scale = 6, nullable = false)
    private BigDecimal cantidadSistema;

    @Column(nullable = false)
    private Boolean resuelto = false;
}
