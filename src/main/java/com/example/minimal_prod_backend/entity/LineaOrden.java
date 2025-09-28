package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lineas_orden")
public class LineaOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private OrdenProduccion orden;

    @Column(name = "numero_linea", nullable = false)
    private int numeroLinea = 1;

    @ManyToOne
    @JoinColumn(name = "producto_componente_id")
    private Producto productoComponente;

    @Column(name = "cantidad_requerida", nullable = false)
    private BigDecimal cantidadRequerida = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "unidad_componente_id")
    private UnidadMedida unidadComponente;

    @Column(name = "cantidad_usada")
    private BigDecimal cantidadUsada = BigDecimal.ZERO;

    @Column(name = "costo_unitario")
    private BigDecimal costoUnitario = BigDecimal.ZERO;

    @Generated
    @Column(name = "costo_total")
    private BigDecimal costoTotal;

    private String observaciones;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn = LocalDateTime.now();
}
