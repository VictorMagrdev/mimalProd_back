package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "centros_costo")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CentroCosto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @Column(unique = true, nullable = false)
    private String nombre;

}

