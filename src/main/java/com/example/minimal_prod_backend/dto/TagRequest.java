package com.example.minimal_prod_backend.dto;

public record TagRequest(
        String nombre,
        String descripcion,
        Long ownerRoleId
) {}