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
@Table(name = "costos_orden")
public class CostoOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private OrdenProduccion orden;

    @ManyToOne
    @JoinColumn(name = "tipo_costo_id")
    private TipoCosto tipoCosto;

    private String descripcion;

    @Column(nullable = false)
    private BigDecimal monto = BigDecimal.ZERO;

    @Column(nullable = false)
    private String moneda = "COP";

    @Column(name = "registrado_en", updatable = false)
    private LocalDateTime registradoEn = LocalDateTime.now();
}

