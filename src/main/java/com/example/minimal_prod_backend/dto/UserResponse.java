package com.example.minimal_prod_backend.dto;

import com.example.minimal_prod_backend.entity.Rol;
import com.example.minimal_prod_backend.entity.Usuario;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private Boolean active;

    public UserResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.email = usuario.getEmail();
        this.active = usuario.getActive();
        if (usuario.getRoles() != null) {
            this.roles = usuario.getRoles().stream().map(Rol::getName).collect(Collectors.toList());
        }
    }

}
