package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.EstadoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.EstadoIncidenciaResponse;
import com.example.minimal_prod_backend.entity.EstadoIncidencia;
import com.example.minimal_prod_backend.mapper.EstadoIncidenciaMapper;
import com.example.minimal_prod_backend.repository.EstadoIncidenciaRepository;
import com.example.minimal_prod_backend.service.EstadoIncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EstadoIncidenciaServiceImpl implements EstadoIncidenciaService {

    private final EstadoIncidenciaRepository repository;
    private final EstadoIncidenciaMapper mapper;

    @Override
    public EstadoIncidenciaResponse create(EstadoIncidenciaRequest request) {
        EstadoIncidencia entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoIncidenciaResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoIncidenciaResponse findById(Long id) {
        return repository.findById(id).map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

