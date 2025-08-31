package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "policies", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"role_id","tag_id","permission_id"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Policy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Permission permission;
}