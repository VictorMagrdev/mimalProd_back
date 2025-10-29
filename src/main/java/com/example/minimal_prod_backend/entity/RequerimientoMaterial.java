package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    @Column(name = "cantidad_requerida", precision = 18, scale = 2)
    private BigDecimal cantidadRequerida;

    @Column(name = "cantidad_disponible", precision = 18, scale = 2)
    @Builder.Default
    private BigDecimal cantidadDisponible = BigDecimal.ZERO;

    @Column(name = "cantidad_a_pedir", precision = 18, scale = 2)
    private BigDecimal cantidadAPedir;

    @Column(name = "fecha_necesidad")
    private LocalDate fechaNecesidad;

    @Column(name = "creado_en")
    @Builder.Default
    private LocalDateTime creadoEn = LocalDateTime.now();
}
