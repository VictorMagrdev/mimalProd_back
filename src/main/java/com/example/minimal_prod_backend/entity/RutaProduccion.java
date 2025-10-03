package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "rutas_produccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RutaProduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id")
    private Long productoId;

    private String version;

    @Column(nullable = false, length = 100)
    private String nombre;

    private Boolean activo = true;

    @Column(name = "creado_en", updatable = false, insertable = false)
    private OffsetDateTime creadoEn;
}