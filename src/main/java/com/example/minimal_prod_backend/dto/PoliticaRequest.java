package com.example.minimal_prod_backend.dto;

public record PoliticaRequest(
        Long rolId,
        Long tagId,
        Long permisoId
) {
}