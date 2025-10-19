package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "maquinas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 150)
    private String descripcion;

    @Column(name = "numero_serie", length = 100)
    private String numeroSerie;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @Column(name = "costo_compra", nullable = false, precision = 18, scale = 2)
    private BigDecimal costoCompra;

    @Column(name = "valor_rescate", precision = 18, scale = 2)
    private BigDecimal valorRescate = BigDecimal.ZERO;

    @Column(name = "vida_util_anios", nullable = false)
    private Integer vidaUtilAnios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_costo_id")
    private CentroCosto centroCosto;

    @Column(nullable = false)
    private Boolean activa = true;

    @Column(name = "creado_en", columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private OffsetDateTime creadoEn = OffsetDateTime.now();
}
