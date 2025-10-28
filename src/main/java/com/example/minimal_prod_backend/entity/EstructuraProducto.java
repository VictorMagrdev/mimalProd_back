package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "estructuras_producto",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"producto_padre_id", "producto_hijo_id", "version"},
                        name = "uq_estructura")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstructuraProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_padre_id", nullable = false)
    private Producto productoPadre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_hijo_id", nullable = false)
    private Producto productoHijo;

    @Column(name = "cantidad", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id")
    private UnidadMedida unidad;

    @Column(name = "version", length = 50)
    private String version;

    @Column(name = "activo")
    @Builder.Default
    private Boolean activo = true;

    @Column(name = "creado_en")
    @Builder.Default
    private LocalDateTime creadoEn = LocalDateTime.now();
}