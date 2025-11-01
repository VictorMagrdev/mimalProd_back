package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.AsignacionRequest;
import com.example.minimal_prod_backend.dto.AsignacionResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.AsignacionMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.AsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
    }

    @Override
    public AsignacionResponse getAsignacionById(Long id) {
        Asignacion asignacion = asignacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignacion not found with id: " + id));
        return asignacionMapper.toResponse(asignacion);
    }

    @Override
    public AsignacionResponse createAsignacion(AsignacionRequest request) {
        validateAsignacionDates(request);

        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(request.ordenTrabajoId())
                .orElseThrow(() -> new ResourceNotFoundException("OrdenTrabajo not found with id: " + request.ordenTrabajoId()));
        Usuario usuario = userRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id: " + request.usuarioId()));
        Usuario asignadoPor = userRepository.findById(request.asignadoPor())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario (asignadoPor) not found with id: " + request.asignadoPor()));
        EstadoAsignacion estadoAsignacion = estadoAsignacionRepository.findById(request.estadoAsignacionId())
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAsignacion not found with id: " + request.estadoAsignacionId()));
        FuncionTarea funcionTarea = funcionTareaRepository.findById(request.funcionTareaId())
                .orElseThrow(() -> new ResourceNotFoundException("FuncionTarea not found with id: " + request.funcionTareaId()));

        Asignacion asignacion = asignacionMapper.toEntity(request);
        asignacion.setOrdenTrabajo(ordenTrabajo);
        asignacion.setUsuario(usuario);
        asignacion.setAsignadoPor(asignadoPor);
        asignacion.setEstadoAsignacion(estadoAsignacion);
        asignacion.setFuncionTarea(funcionTarea);

        return asignacionMapper.toResponse(asignacionRepository.save(asignacion));
    }

    @Override
    public AsignacionResponse updateAsignacion(Long id, AsignacionRequest request) {
        validateAsignacionDates(request);

        Asignacion existingAsignacion = asignacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignacion not found with id: " + id));

        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(request.ordenTrabajoId())
                .orElseThrow(() -> new ResourceNotFoundException("OrdenTrabajo not found with id: " + request.ordenTrabajoId()));
        Usuario usuario = userRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id: " + request.usuarioId()));
        Usuario asignadoPor = userRepository.findById(request.asignadoPor())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario (asignadoPor) not found with id: " + request.asignadoPor()));
        EstadoAsignacion estadoAsignacion = estadoAsignacionRepository.findById(request.estadoAsignacionId())
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAsignacion not found with id: " + request.estadoAsignacionId()));
        FuncionTarea funcionTarea = funcionTareaRepository.findById(request.funcionTareaId())
                .orElseThrow(() -> new ResourceNotFoundException("FuncionTarea not found with id: " + request.funcionTareaId()));

        asignacionMapper.updateEntityFromInput(request, existingAsignacion);

        existingAsignacion.setOrdenTrabajo(ordenTrabajo);
        existingAsignacion.setUsuario(usuario);
        existingAsignacion.setAsignadoPor(asignadoPor);
        existingAsignacion.setEstadoAsignacion(estadoAsignacion);
        existingAsignacion.setFuncionTarea(funcionTarea);

        return asignacionMapper.toResponse(asignacionRepository.save(existingAsignacion));
    }

    private void validateAsignacionDates(AsignacionRequest request) {
        if (request.finPlanificado().isBefore(request.inicioPlanificado())) {
            throw new IllegalArgumentException("La fecha fin debe ser posterior a la fecha inicio");
        }
    }
    @Override
    public void deleteAsignacion(Long id) {
        if (!asignacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asignacion not found with id: " + id);
        }
        asignacionRepository.deleteById(id);
    }
}
