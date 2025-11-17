package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.Incidencia;
import com.example.minimal_prod_backend.entity.IncidenciaArchivo;
import com.example.minimal_prod_backend.entity.TipoArchivo;
import com.example.minimal_prod_backend.entity.Usuario;
import com.example.minimal_prod_backend.repository.UserRepository;
import com.example.minimal_prod_backend.service.client.CloudflareR2Client;
import com.example.minimal_prod_backend.service.client.S3R2Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IncidenciaArchivoService {

    private final UserRepository usuarioRepository;
    private final CloudflareR2Client r2;
    private final S3R2Properties props;

    public List<IncidenciaArchivo> procesar(List<MultipartFile> archivos, Incidencia incidencia, Long usuarioId) throws IOException {

        if (archivos == null || archivos.isEmpty()) return List.of();

        Usuario subidoPor = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado para archivos"));

        return archivos.stream()
                .filter(f -> !f.isEmpty())
                .map(f -> subir(f, incidencia, subidoPor))
                .toList();
    }

    private IncidenciaArchivo subir(MultipartFile file, Incidencia incidencia, Usuario subidoPor) {
        try {
            String nombre = UUID.randomUUID() + "_" + file.getOriginalFilename();

            r2.upload(props.getBucket(), nombre, file.getBytes());

            String url = "%s/%s/%s".formatted(
                    props.getEndpoint(),
                    props.getBucket(),
                    nombre
            );

            return IncidenciaArchivo.builder()
                    .incidencia(incidencia)
                    .subidoPor(subidoPor)
                    .nombreOriginal(file.getOriginalFilename())
                    .tipo(getTipoArchivo(file))
                    .url(url)
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("Error subiendo archivo: " + file.getOriginalFilename(), e);
        }
    }

    private TipoArchivo getTipoArchivo(MultipartFile file) {
        String type = file.getContentType();
        if (type == null) return TipoArchivo.FOTO;
        if (type.startsWith("audio")) return TipoArchivo.AUDIO;
        if (type.startsWith("image")) return TipoArchivo.FOTO;
        throw new IllegalArgumentException("Tipo de archivo no permitido: " + type);
    }
}
