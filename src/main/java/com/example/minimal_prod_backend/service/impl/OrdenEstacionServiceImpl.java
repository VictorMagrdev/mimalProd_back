package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.EstacionProduccionResponse;
import com.example.minimal_prod_backend.dto.OrdenEstacionInput;
import com.example.minimal_prod_backend.dto.OrdenEstacionResponse;
import com.example.minimal_prod_backend.dto.OrdenProduccionResponse;
import com.example.minimal_prod_backend.entity.EstacionProduccion;
import com.example.minimal_prod_backend.entity.OrdenEstacion;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.EstacionProduccionRepository;
import com.example.minimal_prod_backend.repository.OrdenEstacionRepository;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.service.OrdenEstacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenEstacionServiceImpl implements OrdenEstacionService {

    private final OrdenEstacionRepository ordenEstacionRepository;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final EstacionProduccionRepository estacionProduccionRepository;

    @Override
    public List<OrdenEstacionResponse> getOrdenesEstacion() {
        return ordenEstacionRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrdenEstacionResponse getOrdenEstacionById(Long id) {
        OrdenEstacion ordenEstacion = ordenEstacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEstacion not found with id: " + id));
        return toResponse(ordenEstacion);
    }

    @Override
    public OrdenEstacionResponse createOrdenEstacion(OrdenEstacionInput ordenEstacionInput) {
        OrdenEstacion ordenEstacion = toEntity(ordenEstacionInput);
        return toResponse(ordenEstacionRepository.save(ordenEstacion));
    }

    @Override
    public OrdenEstacionResponse updateOrdenEstacion(Long id, OrdenEstacionInput ordenEstacionInput) {
        OrdenEstacion existingOrdenEstacion = ordenEstacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEstacion not found with id: " + id));
        updateEntityFromInput(ordenEstacionInput, existingOrdenEstacion);
        return toResponse(ordenEstacionRepository.save(existingOrdenEstacion));
    }

    @Override
    public void deleteOrdenEstacion(Long id) {
        ordenEstacionRepository.deleteById(id);
    }

    private OrdenEstacionResponse toResponse(OrdenEstacion entity) {
        if (entity == null) return null;
        OrdenEstacionResponse dto = new OrdenEstacionResponse();
        dto.setId(entity.getId());
        dto.setInicioPlanificado(entity.getInicioPlanificado());
        dto.setFinPlanificado(entity.getFinPlanificado());
        dto.setInicioReal(entity.getInicioReal());
        dto.setFinReal(entity.getFinReal());
        dto.setEstado(entity.getEstado());
        dto.setObservaciones(entity.getObservaciones());

        if (entity.getOrden() != null) {
            dto.setOrden(new OrdenProduccionResponse());
            dto.getOrden().setId(entity.getOrden().getId());
        }

        if (entity.getEstacion() != null) {
            dto.setEstacion(new EstacionProduccionResponse());
            dto.getEstacion().setId(entity.getEstacion().getId());
            dto.getEstacion().setNombre(entity.getEstacion().getNombre());
        }

        return dto;
    }

    private OrdenEstacion toEntity(OrdenEstacionInput dto) {
        if (dto == null) return null;
        OrdenEstacion entity = new OrdenEstacion();
        entity.setInicioPlanificado(dto.getInicioPlanificado());
        entity.setFinPlanificado(dto.getFinPlanificado());
        entity.setInicioReal(dto.getInicioReal());
        entity.setFinReal(dto.getFinReal());
        entity.setEstado(dto.getEstado());
        entity.setObservaciones(dto.getObservaciones());

        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + dto.getIdOrden()));
            entity.setOrden(orden);
        }

        if (dto.getIdEstacion() != null) {
            EstacionProduccion estacion = estacionProduccionRepository.findById(dto.getIdEstacion())
                    .orElseThrow(() -> new ResourceNotFoundException("EstacionProduccion not found with id: " + dto.getIdEstacion()));
            entity.setEstacion(estacion);
        }

        return entity;
    }

    private void updateEntityFromInput(OrdenEstacionInput dto, OrdenEstacion entity) {
        if (dto == null || entity == null) return;

        entity.setInicioPlanificado(dto.getInicioPlanificado());
        entity.setFinPlanificado(dto.getFinPlanificado());
        entity.setInicioReal(dto.getInicioReal());
        entity.setFinReal(dto.getFinReal());
        entity.setEstado(dto.getEstado());
        entity.setObservaciones(dto.getObservaciones());

        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + dto.getIdOrden()));
            entity.setOrden(orden);
        }

        if (dto.getIdEstacion() != null) {
            EstacionProduccion estacion = estacionProduccionRepository.findById(dto.getIdEstacion())
                    .orElseThrow(() -> new ResourceNotFoundException("EstacionProduccion not found with id: " + dto.getIdEstacion()));
            entity.setEstacion(estacion);
        }
    }
}
