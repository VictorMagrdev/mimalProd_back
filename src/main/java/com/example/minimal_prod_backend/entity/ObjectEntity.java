package com.example.minimal_prod_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "objects")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ObjectEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @Column(columnDefinition = "jsonb")
    private String metadata;

    @ManyToMany
    @JoinTable(name = "object_tags",
            joinColumns = @JoinColumn(name="object_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id"))
    private Set<Tag> tags = new HashSet<>();
}