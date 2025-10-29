package com.example.minimal_prod_backend.dto;

import com.example.minimal_prod_backend.entity.TipoArchivo;

public record IncidenciaArchivoRequest(
        Long incidenciaId,
        TipoArchivo tipo,
        String nombreOriginal,
        String url
) {
}
