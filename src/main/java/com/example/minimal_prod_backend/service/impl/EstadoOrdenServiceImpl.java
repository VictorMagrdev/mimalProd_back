package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.EstadoOrdenInput;
import com.example.minimal_prod_backend.dto.EstadoOrdenResponse;
import com.example.minimal_prod_backend.entity.EstadoOrden;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.EstadoOrdenRepository;
import com.example.minimal_prod_backend.service.EstadoOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoOrdenServiceImpl implements EstadoOrdenService {

    private final EstadoOrdenRepository estadoOrdenRepository;

    @Override
    public List<EstadoOrdenResponse> getEstadosOrden() {
        return estadoOrdenRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EstadoOrdenResponse getEstadoOrdenById(Integer id) {
        EstadoOrden estadoOrden = estadoOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoOrden not found with id: " + id));
        return toResponse(estadoOrden);
    }

    @Override
    public EstadoOrdenResponse createEstadoOrden(EstadoOrdenInput estadoOrdenInput) {
        EstadoOrden estadoOrden = toEntity(estadoOrdenInput);
        return toResponse(estadoOrdenRepository.save(estadoOrden));
    }

    @Override
    public EstadoOrdenResponse updateEstadoOrden(Integer id, EstadoOrdenInput estadoOrdenInput) {
        EstadoOrden existingEstadoOrden = estadoOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoOrden not found with id: " + id));
        updateEntityFromInput(estadoOrdenInput, existingEstadoOrden);
        return toResponse(estadoOrdenRepository.save(existingEstadoOrden));
    }

    @Override
    public void deleteEstadoOrden(Integer id) {
        estadoOrdenRepository.deleteById(id);
    }

    private EstadoOrdenResponse toResponse(EstadoOrden entity) {
        if (entity == null) return null;
        EstadoOrdenResponse dto = new EstadoOrdenResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setActivo(entity.isActivo());
        dto.setCreadoEn(entity.getCreadoEn());
        return dto;
    }

    private EstadoOrden toEntity(EstadoOrdenInput dto) {
        if (dto == null) return null;
        EstadoOrden entity = new EstadoOrden();
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setActivo(dto.getActivo());
        return entity;
    }

    private void updateEntityFromInput(EstadoOrdenInput dto, EstadoOrden entity) {
        if (dto == null || entity == null) return;
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setActivo(dto.getActivo());
    }
}

