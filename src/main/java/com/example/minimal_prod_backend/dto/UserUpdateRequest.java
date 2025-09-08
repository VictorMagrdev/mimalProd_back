package com.example.minimal_prod_backend.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Data
public class UserUpdateRequest {
    @Email
    private String email;
    private boolean active;
    private List<Long> roleIds;
}
