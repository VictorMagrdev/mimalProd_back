package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.OrdenEventoInput;
import com.example.minimal_prod_backend.dto.OrdenEventoResponse;
import com.example.minimal_prod_backend.dto.OrdenProduccionResponse;
import com.example.minimal_prod_backend.entity.OrdenEvento;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.OrdenEventoRepository;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.service.OrdenEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenEventoServiceImpl implements OrdenEventoService {

    private final OrdenEventoRepository ordenEventoRepository;
    private final OrdenProduccionRepository ordenProduccionRepository;

    @Override
    public List<OrdenEventoResponse> getOrdenesEvento() {
        return ordenEventoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrdenEventoResponse getOrdenEventoById(Long id) {
        OrdenEvento ordenEvento = ordenEventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEvento not found with id: " + id));
        return toResponse(ordenEvento);
    }

    @Override
    public OrdenEventoResponse createOrdenEvento(OrdenEventoInput ordenEventoInput) {
        OrdenEvento ordenEvento = toEntity(ordenEventoInput);
        return toResponse(ordenEventoRepository.save(ordenEvento));
    }

    @Override
    public OrdenEventoResponse updateOrdenEvento(Long id, OrdenEventoInput ordenEventoInput) {
        OrdenEvento existingOrdenEvento = ordenEventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEvento not found with id: " + id));
        updateEntityFromInput(ordenEventoInput, existingOrdenEvento);
        return toResponse(ordenEventoRepository.save(existingOrdenEvento));
    }

    @Override
    public void deleteOrdenEvento(Long id) {
        ordenEventoRepository.deleteById(id);
    }

    private OrdenEventoResponse toResponse(OrdenEvento entity) {
        if (entity == null) return null;
        OrdenEventoResponse dto = new OrdenEventoResponse();
        dto.setId(entity.getId());
        dto.setEvento(entity.getEvento());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFecha(entity.getFecha());

        if (entity.getOrden() != null) {
            dto.setOrden(new OrdenProduccionResponse());
            dto.getOrden().setId(entity.getOrden().getId());
        }

        return dto;
    }

    private OrdenEvento toEntity(OrdenEventoInput dto) {
        if (dto == null) return null;
        OrdenEvento entity = new OrdenEvento();
        entity.setEvento(dto.getEvento());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFecha(dto.getFecha());

        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + dto.getIdOrden()));
            entity.setOrden(orden);
        }

        return entity;
    }

    private void updateEntityFromInput(OrdenEventoInput dto, OrdenEvento entity) {
        if (dto == null || entity == null) return;

        entity.setEvento(dto.getEvento());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFecha(dto.getFecha());

        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + dto.getIdOrden()));
            entity.setOrden(orden);
        }
    }
}
