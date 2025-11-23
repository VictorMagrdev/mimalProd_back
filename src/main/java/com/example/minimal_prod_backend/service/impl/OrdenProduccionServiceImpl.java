package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.OrdenProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.OrdenProduccionResponse;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.entity.Usuario;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.OrdenProduccionMapper;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.security.CustomUserDetailsService;
import com.example.minimal_prod_backend.service.OrdenProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdenProduccionServiceImpl implements OrdenProduccionService {

    private final OrdenProduccionRepository ordenProduccionRepository;
    private final OrdenProduccionMapper mapper;
    private final CustomUserDetailsService userDetail;

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
    public OrdenProduccionResponse createOrdenProduccion(OrdenProduccionRequest input) {
        OrdenProduccion entity = mapper.toEntity(input);
        Usuario currentUser = userDetail.getCurrentUser();
        entity.setCreadoPor(currentUser);
        OrdenProduccion saved = ordenProduccionRepository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public OrdenProduccionResponse updateOrdenProduccion(Long id, OrdenProduccionRequest input) {
        OrdenProduccion existing = ordenProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + id));

        mapper.updateEntityFromInput(input, existing);

        return mapper.toResponse(ordenProduccionRepository.save(existing));
    }

    @Override
    public void deleteOrdenProduccion(Long id) {
        ordenProduccionRepository.deleteById(id);
    }

    @Override
    public Optional<OrdenProduccionResponse> findById(Long id) {
        return ordenProduccionRepository.findById(id).map(mapper::toResponse);
    }

}
