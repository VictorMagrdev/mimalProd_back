package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "ordenes_estacion")
public class OrdenEstacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_id")
    @ToString.Exclude
    private OrdenProduccion orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estacion_id")
    @ToString.Exclude
    private EstacionProduccion estacion;

    @Column(name = "inicio_planificado")
    private OffsetDateTime inicioPlanificado;

    @Column(name = "fin_planificado")
    private OffsetDateTime  finPlanificado;

    @Column(name = "inicio_real")
    private OffsetDateTime  inicioReal;

    @Column(name = "fin_real")
    private OffsetDateTime  finReal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_orden_estacion_id")
    @ToString.Exclude
    private EstadoOrdenEstacion estado;

    @Column(length = 150)
    private String observaciones;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        OrdenEstacion that = (OrdenEstacion) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
