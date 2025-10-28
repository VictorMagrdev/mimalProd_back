package com.example.minimal_prod_backend.dto;

import com.example.minimal_prod_backend.entity.TipoArchivo;

import java.time.OffsetDateTime;

public record IncidenciaArchivoResponse(
        Long id,
        TipoArchivo tipo,
        Long incidenciaId,
        String nombreOriginal,
        String url,
        OffsetDateTime subidoEn
) {}
