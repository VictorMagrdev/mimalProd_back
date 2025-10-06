package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String telefono;

    @Column(name = "codigo_empleado", unique = true, length = 150)
    private String codigoEmpleado;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String apellidos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_costo_id")
    private CentroCosto centroCosto;

    @Builder.Default
    @Column(name = "capacidad_horas_dia", columnDefinition = "interval")
    private Duration capacidadHorasDia = Duration.ofHours(8);

    @Builder.Default
    private Boolean activo = true;

    @CreationTimestamp
    @Column(name = "creado_en", updatable = false)
    private OffsetDateTime creadoEn;

    @UpdateTimestamp
    @Column(name = "actualizado_en")
    private OffsetDateTime actualizadoEn;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    @Builder.Default
    private Set<Rol> roles = new HashSet<>();
}