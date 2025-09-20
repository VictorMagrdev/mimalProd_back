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
@Table(name = "movimiento_inventario")
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", updatable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_bodega_origen")
    private Bodega bodegaOrigen;

    @ManyToOne
    @JoinColumn(name = "id_bodega_destino")
    private  Bodega bodegaDestino;

    @ManyToOne
    @JoinColumn(name = "id_tipo_movimiento")
    private TipoMovimiento tipoMovimiento;

    @Column(nullable = false)
    private  String observaciones;

    @Column(nullable = false)
    private  Long creadoPor;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creado_en = LocalDateTime.now();

    @OneToMany(mappedBy = "movimiento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private java.util.List<MovimientoInventarioDetalle> detalles = new java.util.ArrayList<>();
}
