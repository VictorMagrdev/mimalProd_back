package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "depreciaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Depreciacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maquina_id", nullable = false)
    private Maquina maquina;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_periodo", nullable = false, length = 20)
    private TipoPeriodo tipoPeriodo;

    @Column(nullable = false)
    private LocalDate periodo;

    @Column(name = "depreciacion_periodo", nullable = false, precision = 18, scale = 2)
    private BigDecimal depreciacionPeriodo;

    @Column(name = "depreciacion_acumulada", nullable = false, precision = 18, scale = 2)
    private BigDecimal depreciacionAcumulada;

    @Column(name = "valor_neto", nullable = false, precision = 18, scale = 2)
    private BigDecimal valorNeto;

    @Column(name = "calculado_en", columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    @Builder.Default
    private OffsetDateTime calculadoEn = OffsetDateTime.now();
}

