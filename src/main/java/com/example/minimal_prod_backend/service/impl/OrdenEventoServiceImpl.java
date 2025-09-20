package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.OrdenEventoMapper;
import com.example.minimal_prod_backend.repository.OrdenEventoRepository;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.service.OrdenEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenEventoServiceImpl implements OrdenEventoService {

    private final OrdenEventoRepository ordenEventoRepository;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final OrdenEventoMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenEventoResponse> getOrdenesEvento() {
        return ordenEventoRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenEventoResponse getOrdenEventoById(Long id) {
        OrdenEvento entity = ordenEventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEvento not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public OrdenEventoResponse createOrdenEvento(OrdenEventoInput input) {
        OrdenEvento entity = mapper.toEntity(input);
        attachRelations(input, entity);
        return mapper.toResponse(ordenEventoRepository.save(entity));
    }

    @Override
    @Transactional
    public OrdenEventoResponse updateOrdenEvento(Long id, OrdenEventoInput input) {
        OrdenEvento entity = ordenEventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEvento not found with id: " + id));
        mapper.updateEntityFromInput(input, entity);
        attachRelations(input, entity);
        return mapper.toResponse(ordenEventoRepository.save(entity));
    }

    @Override
    public void deleteOrdenEvento(Long id) {
        ordenEventoRepository.deleteById(id);
    }

    private void attachRelations(OrdenEventoInput dto, OrdenEvento entity) {
        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + dto.getIdOrden()));
            entity.setOrden(orden);
        } else {
            entity.setOrden(null);
        }
    }
}
