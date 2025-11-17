package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.AsignacionRequest;
import com.example.minimal_prod_backend.dto.Response.AsignacionResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.AsignacionMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.AsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsignacionServiceImpl implements AsignacionService {

    private final AsignacionesRepository asignacionRepository;
    private final OrdenTrabajoRepository ordenTrabajoRepository;
    private final UserRepository userRepository;
    private final EstadoAsignacionRepository estadoAsignacionRepository;
    private final FuncionTareaRepository funcionTareaRepository;
    private final AsignacionMapper asignacionMapper;

    @Override
    public List<AsignacionResponse> getAsignaciones() {
        return asignacionRepository.findAll().stream()
                .map(asignacionMapper::toResponse)
                .toList();
    }

    @Override
    public AsignacionResponse getAsignacionById(Long id) {
        Asignacion asignacion = findAsignacionOrThrow(id);
        return asignacionMapper.toResponse(asignacion);
    }

    @Override
    public AsignacionResponse createAsignacion(AsignacionRequest request) {
        validateAsignacionDates(request);

        Asignacion asignacion = asignacionMapper.toEntity(request);
        setForeignEntities(asignacion, request);

        return asignacionMapper.toResponse(asignacionRepository.save(asignacion));
    }

    @Override
    public AsignacionResponse updateAsignacion(Long id, AsignacionRequest request) {
        validateAsignacionDates(request);

        Asignacion asignacion = findAsignacionOrThrow(id);
        asignacionMapper.updateEntityFromInput(request, asignacion);
        setForeignEntities(asignacion, request);

        return asignacionMapper.toResponse(asignacionRepository.save(asignacion));
    }

    private void validateAsignacionDates(AsignacionRequest request) {
        if (request.finPlanificado().isBefore(request.inicioPlanificado())) {
            throw new IllegalArgumentException("La fecha fin debe ser posterior a la fecha inicio");
        }
    }

    private Asignacion findAsignacionOrThrow(Long id) {
        return asignacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignacion not found with id: " + id));
    }

    private void setForeignEntities(Asignacion asignacion, AsignacionRequest request) {

        asignacion.setOrdenTrabajo(
                ordenTrabajoRepository.findById(request.ordenTrabajoId())
                        .orElseThrow(() -> new ResourceNotFoundException("OrdenTrabajo not found"))
        );

        asignacion.setUsuario(
                userRepository.findById(request.usuarioId())
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"))
        );

        asignacion.setAsignadoPor(
                userRepository.findById(request.asignadoPor())
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario asignadoPor not found"))
        );

        asignacion.setEstadoAsignacion(
                estadoAsignacionRepository.findById(request.estadoAsignacionId())
                        .orElseThrow(() -> new ResourceNotFoundException("EstadoAsignacion not found"))
        );

        asignacion.setFuncionTarea(
                funcionTareaRepository.findById(request.funcionTareaId())
                        .orElseThrow(() -> new ResourceNotFoundException("FuncionTarea not found"))
        );
    }

    @Override
    public void deleteAsignacion(Long id) {
        if (!asignacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asignacion not found with id: " + id);
        }
        asignacionRepository.deleteById(id);
    }
}
