package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.IncidenciaArchivoRequest;
import com.example.minimal_prod_backend.dto.IncidenciaArchivoResponse;
import com.example.minimal_prod_backend.entity.IncidenciaArchivo;
import com.example.minimal_prod_backend.mapper.IncidenciaArchivoMapper;
import com.example.minimal_prod_backend.repository.IncidenciaArchivoRepository;
import com.example.minimal_prod_backend.service.IncidenciaArchivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IncidenciaArchivoServiceImpl implements IncidenciaArchivoService {

    private final IncidenciaArchivoRepository repository;
    private final IncidenciaArchivoMapper mapper;

    @Override
    public IncidenciaArchivoResponse create(IncidenciaArchivoRequest request) {
        IncidenciaArchivo entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IncidenciaArchivoResponse> findByIncidencia(Long incidenciaId) {
        return repository.findByIncidenciaId(incidenciaId).stream()
                .map(mapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<IncidenciaArchivoResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }
}
