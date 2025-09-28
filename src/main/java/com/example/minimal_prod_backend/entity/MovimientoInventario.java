package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimientos_inventario")
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "bodega_origen_id")
    private Bodega bodegaOrigen;

    @ManyToOne
    @JoinColumn(name = "bodega_destino_id")
    private Bodega bodegaDestino;

    @ManyToOne
    @JoinColumn(name = "tipo_movimiento_id")
    private TipoMovimiento tipoMovimiento;

    @Column(length = 150)
    private String referencia;

    @Column(length = 150)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "creado_por")
    private Usuario creadoPor;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn = LocalDateTime.now();

    @OneToMany(mappedBy = "movimiento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private java.util.List<MovimientoInventarioDetalle> detalles = new java.util.ArrayList<>();
}
