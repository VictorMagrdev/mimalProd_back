package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "unidades_medida")
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 50)
    private String abreviatura;

    @ManyToOne
    @JoinColumn(name = "unidad_medida_tipo_id", nullable = false)
    private UnidadMedidaTipo tipo;

    @Column(name = "activa")
    private boolean activa = true;

    @Column(name = "base")
    private boolean base = false;

    @CreationTimestamp
    @Column(name = "creado_en", updatable = false)
    private OffsetDateTime creadoEn;
}
