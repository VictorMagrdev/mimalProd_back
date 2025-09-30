package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    private OffsetDateTime creadoEn = OffsetDateTime.now();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        LineaOrden that = (LineaOrden) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
