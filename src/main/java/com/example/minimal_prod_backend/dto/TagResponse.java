package com.example.minimal_prod_backend.dto;

public record TagResponse(
        Long id,
        String nombre,
        String descripcion,
        Long ownerRoleId
) {}