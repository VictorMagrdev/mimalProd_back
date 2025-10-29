package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.OrdenEstacionRequest;
import com.example.minimal_prod_backend.dto.OrdenEstacionResponse;
import com.example.minimal_prod_backend.entity.EstacionProduccion;
import com.example.minimal_prod_backend.entity.EstadoOrdenEstacion;
import com.example.minimal_prod_backend.entity.OrdenEstacion;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.OrdenEstacionMapper;
import com.example.minimal_prod_backend.repository.EstacionProduccionRepository;
import com.example.minimal_prod_backend.repository.EstadoOrdenEstacionRepository;
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
    private final EstadoOrdenEstacionRepository estadoOrdenEstacionRepository;

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
    public OrdenEstacionResponse createOrdenEstacion(OrdenEstacionRequest input) {
        OrdenEstacion entity = mapper.toEntity(input);
        attachRelations(input, entity);
        EstadoOrdenEstacion estado = estadoOrdenEstacionRepository
                .findById(input.estadoOrdenEstacionId())
                .orElseThrow(() -> new ResourceNotFoundException("EstadoOrdenEstacion no encontrado"));
        entity.setEstado(estado);

        return mapper.toResponse(ordenEstacionRepository.save(entity));
    }

    @Override
    @Transactional
    public OrdenEstacionResponse updateOrdenEstacion(Long id, OrdenEstacionRequest input) {
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

    private void attachRelations(OrdenEstacionRequest dto, OrdenEstacion entity) {
        if (dto.ordenId() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.ordenId())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + dto.ordenId()));
            entity.setOrden(orden);
        } else {
            entity.setOrden(null);
        }

        if (dto.estacionId() != null) {
            EstacionProduccion estacion = estacionProduccionRepository.findById(dto.estacionId())
                    .orElseThrow(() -> new ResourceNotFoundException("EstacionProduccion not found with id: " + dto.estacionId()));
            entity.setEstacion(estacion);
        } else {
            entity.setEstacion(null);
        }
    }
}
