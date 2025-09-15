package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.EstacionProduccionInput;
import com.example.minimal_prod_backend.dto.EstacionProduccionResponse;
import com.example.minimal_prod_backend.entity.EstacionProduccion;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.EstacionProduccionRepository;
import com.example.minimal_prod_backend.service.EstacionProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstacionProduccionServiceImpl implements EstacionProduccionService {

    private final EstacionProduccionRepository estacionProduccionRepository;

    @Override
    public List<EstacionProduccionResponse> getEstacionesProduccion() {
        return estacionProduccionRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EstacionProduccionResponse getEstacionProduccionById(Long id) {
        EstacionProduccion estacionProduccion = estacionProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstacionProduccion not found with id: " + id));
        return toResponse(estacionProduccion);
    }

    @Override
    public EstacionProduccionResponse createEstacionProduccion(EstacionProduccionInput estacionProduccionInput) {
        EstacionProduccion estacionProduccion = toEntity(estacionProduccionInput);
        estacionProduccion.setCreadoEn(LocalDateTime.now());
        return toResponse(estacionProduccionRepository.save(estacionProduccion));
    }

    @Override
    public EstacionProduccionResponse updateEstacionProduccion(Long id, EstacionProduccionInput estacionProduccionInput) {
        EstacionProduccion existingEstacionProduccion = estacionProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstacionProduccion not found with id: " + id));
        updateEntityFromInput(estacionProduccionInput, existingEstacionProduccion);
        return toResponse(estacionProduccionRepository.save(existingEstacionProduccion));
    }

    @Override
    public void deleteEstacionProduccion(Long id) {
        estacionProduccionRepository.deleteById(id);
    }

    private EstacionProduccionResponse toResponse(EstacionProduccion entity) {
        if (entity == null) return null;
        EstacionProduccionResponse dto = new EstacionProduccionResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setOrden(entity.getOrden());
        dto.setCreadoEn(entity.getCreadoEn());
        return dto;
    }

    private EstacionProduccion toEntity(EstacionProduccionInput dto) {
        if (dto == null) return null;
        EstacionProduccion entity = new EstacionProduccion();
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setOrden(dto.getOrden());
        return entity;
    }

    private void updateEntityFromInput(EstacionProduccionInput dto, EstacionProduccion entity) {
        if (dto == null || entity == null) return;
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setOrden(dto.getOrden());
    }
}
