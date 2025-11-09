package com.example.minimal_prod_backend.dto.Request;

public record PoliticaRequest(
        Long rolId,
        Long tagId,
        Long permisoId
) {
}