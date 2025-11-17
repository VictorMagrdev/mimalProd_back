package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.IncidenciaConArchivosRequest;
import com.example.minimal_prod_backend.entity.Incidencia;
import com.example.minimal_prod_backend.mapper.IncidenciaCompletaMapper;
import com.example.minimal_prod_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidenciaBuilderService {

    private final TipoIncidenciaRepository tipoIncidenciaRepository;
    private final EstadoIncidenciaRepository estadoRepository;
    private final MaquinaRepository maquinaRepository;
    private final OrdenProduccionRepository ordenRepository;
    private final EstacionProduccionRepository estacionRepository;
    private final UserRepository usuarioRepository;
    private final IncidenciaCompletaMapper mapper;

    public Incidencia build(IncidenciaConArchivosRequest req) {

        Incidencia incidencia = mapper.toEntity(req);

        incidencia.setTipoIncidencia(
                tipoIncidenciaRepository.findById(req.tipoIncidenciaId())
                        .orElseThrow(() -> new IllegalArgumentException("Tipo incidencia no encontrado"))
        );

        incidencia.setEstado(
                estadoRepository.findById(req.estadoId())
                        .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"))
        );

        incidencia.setMaquina(
                maquinaRepository.findById(req.maquinaId())
                        .orElseThrow(() -> new IllegalArgumentException("Máquina no encontrada"))
        );

        incidencia.setOrden(
                ordenRepository.findById(req.ordenId())
                        .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"))
        );

        incidencia.setEstacion(
                estacionRepository.findById(req.estacionId())
                        .orElseThrow(() -> new IllegalArgumentException("Estación no encontrada"))
        );

        incidencia.setReportadoPor(
                usuarioRepository.findById(req.reportadoPor())
                        .orElseThrow(() -> new IllegalArgumentException("Usuario reportante no encontrado"))
        );

        incidencia.setAsignadoA(
                usuarioRepository.findById(req.asignadoA())
                        .orElseThrow(() -> new IllegalArgumentException("Usuario asignado no encontrado"))
        );

        return incidencia;
    }
}

