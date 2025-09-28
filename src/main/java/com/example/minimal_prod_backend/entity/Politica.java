package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "politicas",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"rol_id", "tag_id", "permiso_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Politica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "permiso_id")
    private Permiso permiso;
}
