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
@Table(name = "costo_orden")
public class CostoOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_orden")
    private OrdenProduccion orden;

    @ManyToOne
    @JoinColumn(name = "id_tipo_costo")
    private TipoCosto tipoCosto;

    private String descripcion;

    @Column(nullable = false)
    private BigDecimal monto = BigDecimal.ZERO;

    private String moneda = "COP";

    @Column(name = "registrado_en")
    private LocalDateTime registradoEn = LocalDateTime.now();
}
