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
@Table(name = "movimiento_inventario_detalle")
public class MovimientoInventarioDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimiento_id")
    private MovimientoInventario movimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id")
    private LoteProduccion lote;

    @Column(nullable = false, precision = 18, scale = 6)
    private BigDecimal cantidad = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id")
    private UnidadMedida unidad;

    @Column(name = "costo_unitario", precision = 18, scale = 6)
    private BigDecimal costoUnitario = BigDecimal.ZERO;

    @Column(name = "costo_total", precision = 18, scale = 6, insertable = false, updatable = false)
    private BigDecimal costoTotal;
}
