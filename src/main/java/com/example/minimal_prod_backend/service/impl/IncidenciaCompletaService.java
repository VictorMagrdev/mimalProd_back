package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.IncidenciaConArchivosRequest;
import com.example.minimal_prod_backend.dto.Response.IncidenciaResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.mapper.IncidenciaCompletaMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.client.S3R2Properties;
import com.example.minimal_prod_backend.service.client.CloudflareR2Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class IncidenciaCompletaService {

    private final IncidenciaRepository incidenciaRepository;
    private final IncidenciaArchivoRepository archivoRepository;
    private final TipoIncidenciaRepository tipoIncidenciaRepository;
    private final EstadoIncidenciaRepository estadoRepository;
    private final MaquinaRepository maquinaRepository;
    private final OrdenProduccionRepository ordenRepository;
    private final EstacionProduccionRepository estacionRepository;
    private final UserRepository usuarioRepository;
    private final CloudflareR2Client r2;
    private final S3R2Properties props;
    private final IncidenciaCompletaMapper incidenciaMapper;

    public IncidenciaResponse crearConArchivos(IncidenciaConArchivosRequest req) throws IOException {
        Incidencia incidencia = buildIncidencia(req);
        Incidencia incidenciaGuardada = incidenciaRepository.save(incidencia);

        if (req.archivos() != null && !req.archivos().isEmpty()) {
            List<IncidenciaArchivo> archivos = procesarArchivos(req.archivos(), incidenciaGuardada, req.reportadoPor());
            archivoRepository.saveAll(archivos);
        }

        return incidenciaMapper.toResponse(
                incidenciaRepository.findById(incidenciaGuardada.getId()).orElse(incidenciaGuardada)
        );
    }

    private Incidencia buildIncidencia(IncidenciaConArchivosRequest req) {
        TipoIncidencia tipo = tipoIncidenciaRepository.findById(req.tipoIncidenciaId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de incidencia no encontrado"));
        EstadoIncidencia estado = estadoRepository.findById(req.estadoId())
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));
        Maquina maquina = maquinaRepository.findById(req.maquinaId())
                .orElseThrow(() -> new IllegalArgumentException("Máquina no encontrada"));
        OrdenProduccion orden = ordenRepository.findById(req.ordenId())
                .orElseThrow(() -> new IllegalArgumentException("Orden de producción no encontrada"));
        EstacionProduccion estacion = estacionRepository.findById(req.estacionId())
                .orElseThrow(() -> new IllegalArgumentException("Estación de producción no encontrada"));
        Usuario reportadoPor = usuarioRepository.findById(req.reportadoPor())
                .orElseThrow(() -> new IllegalArgumentException("Usuario (reportado por) no encontrado"));
        Usuario asignadoA = usuarioRepository.findById(req.asignadoA())
                .orElseThrow(() -> new IllegalArgumentException("Usuario (asignado a) no encontrado"));

        Incidencia incidencia = incidenciaMapper.toEntity(req);
        incidencia.setTipoIncidencia(tipo);
        incidencia.setEstado(estado);
        incidencia.setMaquina(maquina);
        incidencia.setOrden(orden);
        incidencia.setEstacion(estacion);
        incidencia.setReportadoPor(reportadoPor);
        incidencia.setAsignadoA(asignadoA);
        return incidencia;
    }

    private List<IncidenciaArchivo> procesarArchivos(List<MultipartFile> archivos, Incidencia incidencia, Long reportadoPorId) throws IOException {
        Usuario subidoPor = usuarioRepository.findById(reportadoPorId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado para archivos"));

        return archivos.stream()
                .filter(file -> !file.isEmpty())
                .map(file -> subirArchivo(file, incidencia, subidoPor))
                .toList();
    }

    private IncidenciaArchivo subirArchivo(MultipartFile file, Incidencia incidencia, Usuario subidoPor) {
        try {
            String nombre = UUID.randomUUID() + "_" + file.getOriginalFilename();
            r2.upload(props.getBucket(), nombre, file.getBytes());
            String url = String.format("%s/%s/%s", props.getEndpoint(), props.getBucket(), nombre);

            TipoArchivo tipo = getTipoArchivo(file);
            return IncidenciaArchivo.builder()
                    .incidencia(incidencia)
                    .subidoPor(subidoPor)
                    .nombreOriginal(file.getOriginalFilename())
                    .tipo(tipo)
                    .url(url)
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("Error al subir archivo: " + file.getOriginalFilename(), e);
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
