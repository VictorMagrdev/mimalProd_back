package com.example.minimal_prod_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

public record IncidenciaConArchivosRequest(
        @NotBlank String codigo,
        @NotBlank String titulo,
        String descripcion,
        @NotNull Long tipoIncidenciaId,
        @NotNull Long estadoId,
        OffsetDateTime fechaCierre,
        Duration tiempoParada,
        List<MultipartFile> archivos
) {
    public IncidenciaConArchivosRequest withArchivos(List<MultipartFile> newArchivos) {
        return new IncidenciaConArchivosRequest(
                codigo, titulo, descripcion, tipoIncidenciaId, estadoId,
                fechaCierre, tiempoParada, newArchivos
        );
    }
}