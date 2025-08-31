package com.example.minimal_prod_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PolicyRequest {
    @NotNull
    private Long roleId;
    @NotNull
    private Long tagId;
    @NotNull
    private Long permissionId;
}
