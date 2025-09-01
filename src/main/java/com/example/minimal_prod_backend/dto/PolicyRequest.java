package com.example.minimal_prod_backend.dto;

import jakarta.validation.constraints.NotNull;

public record PolicyRequest(
        @NotNull Long roleId,
        @NotNull Long tagId,
        @NotNull Long permissionId
) {}