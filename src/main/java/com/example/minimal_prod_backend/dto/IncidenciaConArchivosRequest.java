package com.example.minimal_prod_backend.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

public record IncidenciaConArchivosRequest(
        String codigo,
        String titulo,
        String descripcion,
        Long tipoIncidenciaId,
        Long estadoId,
        OffsetDateTime fechaCierre,
        Duration tiempoParada,
        List<MultipartFile> archivos
) {
}
