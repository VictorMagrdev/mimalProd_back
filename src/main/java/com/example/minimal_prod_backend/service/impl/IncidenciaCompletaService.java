package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.IncidenciaConArchivosRequest;
import com.example.minimal_prod_backend.dto.IncidenciaResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.mapper.IncidenciaCompletaMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.security.S3R2Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    public IncidenciaResponse crearConArchivos(IncidenciaConArchivosRequest req) throws Exception {
        TipoIncidencia tipoIncidencia = tipoIncidenciaRepository.findById(req.tipoIncidenciaId())
                .orElseThrow(() -> new RuntimeException("Tipo de incidencia no encontrado"));

        EstadoIncidencia estado = estadoRepository.findById(req.estadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        Maquina maquina = maquinaRepository.findById(req.maquinaId())
                .orElseThrow(() -> new RuntimeException("Máquina no encontrada"));

        OrdenProduccion orden = ordenRepository.findById(req.ordenId())
                .orElseThrow(() -> new RuntimeException("Orden de producción no encontrada"));

        EstacionProduccion estacion = estacionRepository.findById(req.estacionId())
                .orElseThrow(() -> new RuntimeException("Estación de producción no encontrada"));

        Usuario reportadoPor = usuarioRepository.findById(req.reportadoPor())
                .orElseThrow(() -> new RuntimeException("Usuario (reportado por) no encontrado"));

        Usuario asignadoA = usuarioRepository.findById(req.asignadoA())
                .orElseThrow(() -> new RuntimeException("Usuario (asignado a) no encontrado"));

        Incidencia incidencia = incidenciaMapper.toEntity(req);
        incidencia.setTipoIncidencia(tipoIncidencia);
        incidencia.setEstado(estado);
        incidencia.setMaquina(maquina);
        incidencia.setOrden(orden);
        incidencia.setEstacion(estacion);
        incidencia.setReportadoPor(reportadoPor);
        incidencia.setAsignadoA(asignadoA);

        Incidencia incidenciaGuardada = incidenciaRepository.save(incidencia);

        if (req.archivos() != null && !req.archivos().isEmpty()) {
            for (MultipartFile file : req.archivos()) {
                if (!file.isEmpty()) {
                    String nombre = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    r2.upload(props.getBucket(), nombre, file.getBytes());
                    String url = String.format("%s/%s/%s", props.getEndpoint(), props.getBucket(), nombre);

                    TipoArchivo tipo = file.getContentType() != null && file.getContentType().startsWith("audio")
                            ? TipoArchivo.AUDIO : TipoArchivo.FOTO;

                    IncidenciaArchivo archivo = IncidenciaArchivo.builder()
                            .incidencia(incidenciaGuardada)
                            .subidoPor(reportadoPor)
                            .nombreOriginal(file.getOriginalFilename())
                            .tipo(tipo)
                            .url(url)
                            .build();
                    archivoRepository.save(archivo);
                }
            }
        }

        Incidencia incidenciaCompleta = incidenciaRepository.findById(incidenciaGuardada.getId())
                .orElse(incidenciaGuardada);

        return incidenciaMapper.toResponse(incidenciaCompleta);
    }
}