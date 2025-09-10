package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orden_produccion")
public class OrdenProduccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_orden", unique = true, nullable = false)
    private String numeroOrden;

    @ManyToOne
    @JoinColumn(name = "id_lote")
    private LoteProduccion lote;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(nullable = false)
    private BigDecimal cantidad = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "id_unidad")
    private UnidadMedida unidad;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoOrden estado;

    @Column(name = "inicio_planificado")
    private LocalDateTime inicioPlanificado;

    @Column(name = "fin_planificado")
    private LocalDateTime finPlanificado;

    @Column(name = "inicio_real")
    private LocalDateTime inicioReal;

    @Column(name = "fin_real")
    private LocalDateTime finReal;

    @Column(name = "cantidad_desperdicio")
    private BigDecimal cantidadDesperdicio = BigDecimal.ZERO;

    @Column(name = "cantidad_producida")
    private BigDecimal cantidadProducida = BigDecimal.ZERO;

    @Column(name = "creado_por")
    private Integer creadoPor;

    private String observaciones;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn = LocalDateTime.now();

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn = LocalDateTime.now();
}
