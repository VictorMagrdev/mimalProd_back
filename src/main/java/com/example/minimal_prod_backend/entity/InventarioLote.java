package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "inventarios_lote",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_producto_lote_bodega",
                        columnNames = {"producto_id", "lote_id", "bodega_id"}
                )
        }
)
public class InventarioLote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id")
    private LoteProduccion lote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bodega_id")
    private Bodega bodega;

    @Column(nullable = false, precision = 18, scale = 6)
    private BigDecimal cantidad = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id")
    private UnidadMedida unidad;

    @Column(name = "actualizado_en", insertable = false, updatable = false)
    private LocalDateTime actualizadoEn;
}
