package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "unidad_conversion")
public class UnidadConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_origen", nullable = false)
    private UnidadMedida origen;

    @ManyToOne
    @JoinColumn(name = "id_destino", nullable = false)
    private UnidadMedida destino;

    @Column(nullable = false)
    private BigDecimal factor;
}
