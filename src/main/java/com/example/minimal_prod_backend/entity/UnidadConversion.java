package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "unidades_conversion",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_conversion", columnNames = {"origen_id", "destino_id"})
        }
)
public class UnidadConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origen_id", nullable = false)
    private UnidadMedida origen;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private UnidadMedida destino;

    @Column(nullable = false, precision = 18, scale = 8)
    private BigDecimal factor;
}
