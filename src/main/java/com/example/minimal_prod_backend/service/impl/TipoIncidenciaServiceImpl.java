package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.TipoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.TipoIncidenciaResponse;
import com.example.minimal_prod_backend.entity.TipoIncidencia;
import com.example.minimal_prod_backend.mapper.TipoIncidenciaMapper;
import com.example.minimal_prod_backend.repository.TipoIncidenciaRepository;
import com.example.minimal_prod_backend.service.TipoIncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TipoIncidenciaServiceImpl implements TipoIncidenciaService {

    private final TipoIncidenciaRepository repository;
    private final TipoIncidenciaMapper mapper;

    @Override
    public TipoIncidenciaResponse create(TipoIncidenciaRequest request) {
        TipoIncidencia entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoIncidenciaResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoIncidenciaResponse findById(Long id) {
        return repository.findById(id).map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
