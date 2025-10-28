package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.IncidenciaRequest;
import com.example.minimal_prod_backend.dto.IncidenciaResponse;
import com.example.minimal_prod_backend.entity.Incidencia;
import com.example.minimal_prod_backend.mapper.IncidenciaMapper;
import com.example.minimal_prod_backend.repository.IncidenciaRepository;
import com.example.minimal_prod_backend.service.IncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository repository;
    private final IncidenciaMapper mapper;

    @Override
    public IncidenciaResponse create(IncidenciaRequest request) {
        Incidencia entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IncidenciaResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public IncidenciaResponse findById(Long id) {
        return repository.findById(id).map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
