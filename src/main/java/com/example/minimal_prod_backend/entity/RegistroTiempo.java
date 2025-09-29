package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registros_tiempo")
@Check(constraints = "fin_tz > inicio_tz")
public class RegistroTiempo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignacion_id")
    @ToString.Exclude
    private Asignacion asignacion;

    @Column(name = "inicio_tz", nullable = false)
    private OffsetDateTime inicioTz;

    @Column(name = "fin_tz", nullable = false)
    private OffsetDateTime finTz;

    @Column(name = "duracion", insertable = false, updatable = false)
    private String duracion;

    @Column(name = "duracion_horas", insertable = false, updatable = false)
    private Double duracionHoras;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_actividad_id")
    @ToString.Exclude
    private TipoActividad tipoActividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_costo_id")
    @ToString.Exclude
    private TipoCosto tipoCosto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_aprobacion_id", nullable = false)
    @ToString.Exclude
    private EstadoAprobacion estadoAprobacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoja_timesheet_id")
    @ToString.Exclude
    private HojaTimesheet hojaTimesheet;

    @CreationTimestamp
    @Column(name = "creado_en", updatable = false)
    private OffsetDateTime creadoEn;

    @UpdateTimestamp
    @Column(name = "actualizado_en")
    private OffsetDateTime actualizadoEn;
}
