package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "operaciones_orden_estados")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class OperacionOrdenEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    private String descripcion;
}
