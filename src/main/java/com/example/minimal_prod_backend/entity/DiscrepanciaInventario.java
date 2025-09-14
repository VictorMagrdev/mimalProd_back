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
@Table(name = "discrepancia_inventario")
public class DiscrepanciaInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conteo")
    private ConteoCiclico conteo;

    @Column(name = "cantidad_sistema", precision = 18, scale = 6, nullable = false)
    private BigDecimal cantidadSistema;

    @Column(name = "cantidad_contada", precision = 18, scale = 6, nullable = false)
    private BigDecimal cantidadContada;

    @Column(name = "diferencia", precision = 18, scale = 6, insertable = false, updatable = false)
    private BigDecimal diferencia;

    @Column(nullable = false)
    private Boolean resuelto = false;
}