package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.EstacionProduccionRepository;
import com.example.minimal_prod_backend.repository.OrdenEstacionRepository;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.service.OrdenEstacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenEstacionServiceImpl implements OrdenEstacionService {

    private final OrdenEstacionRepository ordenEstacionRepository;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final EstacionProduccionRepository estacionProduccionRepository;
    private final OrdenEstacionMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenEstacionResponse> getOrdenesEstacion() {
        return ordenEstacionRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenEstacionResponse getOrdenEstacionById(Long id) {
        OrdenEstacion entity = ordenEstacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEstacion not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public OrdenEstacionResponse createOrdenEstacion(OrdenEstacionInput input) {
        OrdenEstacion entity = mapper.toEntity(input);
        attachRelations(input, entity);
        return mapper.toResponse(ordenEstacionRepository.save(entity));
    }

    @Override
    @Transactional
    public OrdenEstacionResponse updateOrdenEstacion(Long id, OrdenEstacionInput input) {
        OrdenEstacion entity = ordenEstacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEstacion not found with id: " + id));
        mapper.updateEntityFromInput(input, entity);
        attachRelations(input, entity);
        return mapper.toResponse(ordenEstacionRepository.save(entity));
    }

    @Override
    public void deleteOrdenEstacion(Long id) {
        ordenEstacionRepository.deleteById(id);
    }

    private void attachRelations(OrdenEstacionInput dto, OrdenEstacion entity) {
        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + dto.getIdOrden()));
            entity.setOrden(orden);
        } else {
            entity.setOrden(null);
        }

        if (dto.getIdEstacion() != null) {
            EstacionProduccion estacion = estacionProduccionRepository.findById(dto.getIdEstacion())
                    .orElseThrow(() -> new ResourceNotFoundException("EstacionProduccion not found with id: " + dto.getIdEstacion()));
            entity.setEstacion(estacion);
        } else {
            entity.setEstacion(null);
        }
    }
}
