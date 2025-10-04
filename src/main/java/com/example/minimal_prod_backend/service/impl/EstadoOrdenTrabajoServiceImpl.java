package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.EstadoOrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.EstadoOrdenTrabajoResponse;
import com.example.minimal_prod_backend.entity.EstadoOrdenTrabajo;
import com.example.minimal_prod_backend.mapper.EstadoOrdenTrabajoMapper;
import com.example.minimal_prod_backend.repository.EstadoOrdenTrabajoRepository;
import com.example.minimal_prod_backend.service.EstadoOrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoOrdenTrabajoServiceImpl implements EstadoOrdenTrabajoService {

    @Autowired
    private EstadoOrdenTrabajoRepository estadoOrdenTrabajoRepository;

    @Autowired
    private EstadoOrdenTrabajoMapper estadoOrdenTrabajoMapper;

    @Override
    public List<EstadoOrdenTrabajoResponse> findAll() {
        return estadoOrdenTrabajoRepository.findAll().stream()
                .map(estadoOrdenTrabajoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EstadoOrdenTrabajoResponse findById(Long id) {
        EstadoOrdenTrabajo estadoOrdenTrabajo = estadoOrdenTrabajoRepository.findById(id).orElse(null);
        return estadoOrdenTrabajoMapper.toResponse(estadoOrdenTrabajo);
    }

    @Override
    public EstadoOrdenTrabajoResponse save(EstadoOrdenTrabajoRequest estadoOrdenTrabajoRequest) {
        EstadoOrdenTrabajo estadoOrdenTrabajo = estadoOrdenTrabajoMapper.toEntity(estadoOrdenTrabajoRequest);
        return estadoOrdenTrabajoMapper.toResponse(estadoOrdenTrabajoRepository.save(estadoOrdenTrabajo));
    }

    @Override
    public void deleteById(Long id) {
        estadoOrdenTrabajoRepository.deleteById(id);
    }
}
