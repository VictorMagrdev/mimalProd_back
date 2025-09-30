package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.EstadoAsignacionRequest;
import com.example.minimal_prod_backend.dto.EstadoAsignacionResponse;
import com.example.minimal_prod_backend.entity.EstadoAsignacion;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.EstadoAsignacionMapper;
import com.example.minimal_prod_backend.repository.EstadoAsignacionRepository;
import com.example.minimal_prod_backend.service.EstadoAsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoAsignacionServiceImpl implements EstadoAsignacionService {

    private final EstadoAsignacionRepository estadoAsignacionRepository;
    private final EstadoAsignacionMapper estadoAsignacionMapper;

    @Override
    public List<EstadoAsignacionResponse> getEstadosAsignacion() {
        return estadoAsignacionRepository.findAll().stream()
                .map(estadoAsignacionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EstadoAsignacionResponse getEstadoAsignacionById(Long id) {
        EstadoAsignacion estado = estadoAsignacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAsignacion not found with id: " + id));
        return estadoAsignacionMapper.toResponse(estado);
    }

    @Override
    public EstadoAsignacionResponse createEstadoAsignacion(EstadoAsignacionRequest request) {
        EstadoAsignacion estado = estadoAsignacionMapper.toEntity(request);
        return estadoAsignacionMapper.toResponse(estadoAsignacionRepository.save(estado));
    }

    @Override
    public EstadoAsignacionResponse updateEstadoAsignacion(Long id, EstadoAsignacionRequest request) {
        EstadoAsignacion existingEstado = estadoAsignacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAsignacion not found with id: " + id));

        existingEstado.setNombre(request.nombre());
        existingEstado.setDescripcion(request.descripcion());

        return estadoAsignacionMapper.toResponse(estadoAsignacionRepository.save(existingEstado));
    }

    @Override
    public void deleteEstadoAsignacion(Long id) {
        if (!estadoAsignacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("EstadoAsignacion not found with id: " + id);
        }
        estadoAsignacionRepository.deleteById(id);
    }
}
