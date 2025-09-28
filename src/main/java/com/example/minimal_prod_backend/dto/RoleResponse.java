package com.example.minimal_prod_backend.dto;

import com.example.minimal_prod_backend.entity.Rol;
import lombok.Data;

@Data
public class RoleResponse {
    private Long id;
    private String name;
    private String description;

    public RoleResponse(Rol role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
    }
}
