package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "requerimientos_materiales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequerimientoMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_produccion_id")
    private OrdenProduccion ordenProduccion;

    @Column(name = "cantidad_requerida", precision = 18, scale = 6)
    private Double cantidadRequerida;

    @Column(name = "cantidad_disponible", precision = 18, scale = 6)
    @Builder.Default
    private Double cantidadDisponible = 0.0;

    @Column(name = "cantidad_a_pedir", precision = 18, scale = 6, insertable = false, updatable = false)
    private Double cantidadAPedir;

    @Column(name = "fecha_necesidad")
    private LocalDate fechaNecesidad;

    @Column(name = "creado_en")
    @Builder.Default
    private LocalDateTime creadoEn = LocalDateTime.now();
}