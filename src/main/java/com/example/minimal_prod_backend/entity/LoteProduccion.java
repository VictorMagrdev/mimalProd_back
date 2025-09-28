package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lotes_produccion")
public class LoteProduccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_lote", unique = true, nullable = false, length = 100)
    private String numeroLote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column(name = "fabricado_en")
    private OffsetDateTime fabricadoEn;

    @Column(name = "vence_en")
    private OffsetDateTime venceEn;

    @CreationTimestamp
    @Column(name = "creado_en", updatable = false)
    private OffsetDateTime creadoEn;

    @ManyToMany(mappedBy = "lotes")
    private Set<OrdenProduccion> ordenes = new HashSet<>();

}
