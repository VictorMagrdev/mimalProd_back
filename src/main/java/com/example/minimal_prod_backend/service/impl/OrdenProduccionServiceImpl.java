package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.OrdenProduccionMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.OrdenProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenProduccionServiceImpl implements OrdenProduccionService {

    private final OrdenProduccionRepository ordenProduccionRepository;
    private final OrdenProduccionMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenProduccionResponse> getOrdenesProduccion() {
        return mapper.toResponseList(ordenProduccionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenProduccionResponse getOrdenProduccionById(Long id) {
        OrdenProduccion ordenProduccion = ordenProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + id));
        return mapper.toResponse(ordenProduccion);
    }

    @Override
    @Transactional
    public OrdenProduccionResponse createOrdenProduccion(OrdenProduccionInput input) {
        OrdenProduccion entity = mapper.toEntity(input);
        OrdenProduccion saved = ordenProduccionRepository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public OrdenProduccionResponse updateOrdenProduccion(Long id, OrdenProduccionInput input) {
        OrdenProduccion existing = ordenProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + id));

        mapper.updateEntityFromInput(input, existing);

        return mapper.toResponse(ordenProduccionRepository.save(existing));
    }

    @Override
    public void deleteOrdenProduccion(Long id) {
        ordenProduccionRepository.deleteById(id);
    }

}
