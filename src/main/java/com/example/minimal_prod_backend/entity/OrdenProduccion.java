package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordenes_produccion")
public class OrdenProduccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_orden", unique = true, nullable = false, length = 100)
    private String numeroOrden;

    @Column(nullable = false, precision = 18, scale = 6)
    private BigDecimal cantidad = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id")
    private UnidadMedida unidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private EstadoOrden estado;

    @Column(name = "inicio_planificado")
    private OffsetDateTime inicioPlanificado;

    @Column(name = "fin_planificado")
    private OffsetDateTime finPlanificado;

    @Column(name = "inicio_real")
    private OffsetDateTime inicioReal;

    @Column(name = "fin_real")
    private OffsetDateTime finReal;

    @Column(name = "cantidad_desperdicio", precision = 18, scale = 6)
    private BigDecimal cantidadDesperdicio = BigDecimal.ZERO;

    @Column(name = "cantidad_producida", precision = 18, scale = 6)
    private BigDecimal cantidadProducida = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por")
    private Usuario creadoPor;

    @Column(length = 150)
    private String observaciones;

    @CreationTimestamp
    @Column(name = "creado_en", updatable = false)
    private OffsetDateTime creadoEn;

    @UpdateTimestamp
    @Column(name = "actualizado_en")
    private OffsetDateTime actualizadoEn;

    @ManyToMany
    @JoinTable(
            name = "ordenes_lotes",
            joinColumns = @JoinColumn(name = "orden_id"),
            inverseJoinColumns = @JoinColumn(name = "lote_id")
    )
    private Set<LoteProduccion> lotes = new HashSet<>();

}