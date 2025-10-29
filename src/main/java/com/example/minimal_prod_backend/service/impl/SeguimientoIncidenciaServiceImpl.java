package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.SeguimientoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.SeguimientoIncidenciaResponse;
import com.example.minimal_prod_backend.entity.SeguimientoIncidencia;
import com.example.minimal_prod_backend.mapper.SeguimientoIncidenciaMapper;
import com.example.minimal_prod_backend.repository.SeguimientoIncidenciaRepository;
import com.example.minimal_prod_backend.service.SeguimientoIncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SeguimientoIncidenciaServiceImpl implements SeguimientoIncidenciaService {

    private final SeguimientoIncidenciaRepository repository;
    private final SeguimientoIncidenciaMapper mapper;

    @Override
    public SeguimientoIncidenciaResponse create(SeguimientoIncidenciaRequest request) {
        SeguimientoIncidencia entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeguimientoIncidenciaResponse> findByIncidencia(Long incidenciaId) {
        return repository.findByIncidenciaIdOrderByRealizadoEnAsc(incidenciaId)
                .stream().map(mapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
