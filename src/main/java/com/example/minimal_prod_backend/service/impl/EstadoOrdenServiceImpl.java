package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.EstadoOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoOrdenResponse;
import com.example.minimal_prod_backend.entity.EstadoOrden;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.EstadoOrdenMapper;
import com.example.minimal_prod_backend.repository.EstadoOrdenRepository;
import com.example.minimal_prod_backend.service.EstadoOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoOrdenServiceImpl implements EstadoOrdenService {

    private final EstadoOrdenRepository estadoOrdenRepository;
    private final EstadoOrdenMapper estadoOrdenMapper;

    @Override
    public List<EstadoOrdenResponse> getEstadosOrden() {
        return estadoOrdenMapper.toResponseList(estadoOrdenRepository.findAll());
    }

    @Override
    public EstadoOrdenResponse getEstadoOrdenById(Long id) {
        EstadoOrden estadoOrden = estadoOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoOrden not found with id: " + id));
        return estadoOrdenMapper.toResponse(estadoOrden);
    }

    @Override
    public EstadoOrdenResponse createEstadoOrden(EstadoOrdenRequest estadoOrdenInput) {
        EstadoOrden estadoOrden = estadoOrdenMapper.toEntity(estadoOrdenInput);
        return estadoOrdenMapper.toResponse(estadoOrdenRepository.save(estadoOrden));
    }

    @Override
    public EstadoOrdenResponse updateEstadoOrden(Long id, EstadoOrdenRequest estadoOrdenInput) {
        EstadoOrden existingEstadoOrden = estadoOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoOrden not found with id: " + id));
        estadoOrdenMapper.updateEntityFromInput(estadoOrdenInput, existingEstadoOrden);
        return estadoOrdenMapper.toResponse(estadoOrdenRepository.save(existingEstadoOrden));
    }

    @Override
    public void deleteEstadoOrden(Long id) {
        estadoOrdenRepository.deleteById(id);
    }
}
