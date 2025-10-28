package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estados_incidencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer orden = 0;

    @Column(name = "estado_final")
    private Boolean estadoFinal = false;
}
