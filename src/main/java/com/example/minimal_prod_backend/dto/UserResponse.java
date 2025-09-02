package com.example.minimal_prod_backend.dto;

import com.example.minimal_prod_backend.entity.Role;
import com.example.minimal_prod_backend.entity.User;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Set<String> roles;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        if (user.getRoles() != null) {
            this.roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        }
    }

    public Set<String> getRoles() {
        return roles;
    }
}
