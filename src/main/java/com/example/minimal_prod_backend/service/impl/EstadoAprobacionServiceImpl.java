package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.EstadoAprobacionRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoAprobacionResponse;
import com.example.minimal_prod_backend.entity.EstadoAprobacion;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.EstadoAprobacionMapper;
import com.example.minimal_prod_backend.repository.EstadoAprobacionRepository;
import com.example.minimal_prod_backend.service.EstadoAprobacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoAprobacionServiceImpl implements EstadoAprobacionService {

    private final EstadoAprobacionRepository estadoAprobacionRepository;
    private final EstadoAprobacionMapper estadoAprobacionMapper;

    @Override
    public List<EstadoAprobacionResponse> getEstadosAprobacion() {
        return estadoAprobacionRepository.findAll().stream()
                .map(estadoAprobacionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EstadoAprobacionResponse getEstadoAprobacionById(Long id) {
        EstadoAprobacion estado = estadoAprobacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAprobacion not found with id: " + id));
        return estadoAprobacionMapper.toResponse(estado);
    }

    @Override
    public EstadoAprobacionResponse createEstadoAprobacion(EstadoAprobacionRequest request) {
        EstadoAprobacion estado = estadoAprobacionMapper.toEntity(request);
        return estadoAprobacionMapper.toResponse(estadoAprobacionRepository.save(estado));
    }

    @Override
    public EstadoAprobacionResponse updateEstadoAprobacion(Long id, EstadoAprobacionRequest request) {
        EstadoAprobacion existingEstado = estadoAprobacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAprobacion not found with id: " + id));

        existingEstado.setNombre(request.nombre());
        existingEstado.setDescripcion(request.descripcion());

        return estadoAprobacionMapper.toResponse(estadoAprobacionRepository.save(existingEstado));
    }

    @Override
    public void deleteEstadoAprobacion(Long id) {
        if (!estadoAprobacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("EstadoAprobacion not found with id: " + id);
        }
        estadoAprobacionRepository.deleteById(id);
    }
}
