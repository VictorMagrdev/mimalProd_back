package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.EstadoOrdenEstacionRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoOrdenEstacionResponse;
import com.example.minimal_prod_backend.entity.EstadoOrdenEstacion;
import com.example.minimal_prod_backend.mapper.EstadoOrdenEstacionMapper;
import com.example.minimal_prod_backend.repository.EstadoOrdenEstacionRepository;
import com.example.minimal_prod_backend.service.EstadoOrdenEstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoOrdenEstacionServiceImpl implements EstadoOrdenEstacionService {

    @Autowired
    private EstadoOrdenEstacionRepository estadoOrdenEstacionRepository;

    @Autowired
    private EstadoOrdenEstacionMapper estadoOrdenEstacionMapper;

    @Override
    public List<EstadoOrdenEstacionResponse> findAll() {
        return estadoOrdenEstacionRepository.findAll().stream()
                .map(estadoOrdenEstacionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EstadoOrdenEstacionResponse findById(Long id) {
        EstadoOrdenEstacion estadoOrdenEstacion = estadoOrdenEstacionRepository.findById(id).orElse(null);
        return estadoOrdenEstacionMapper.toResponse(estadoOrdenEstacion);
    }

    @Override
    public EstadoOrdenEstacionResponse save(EstadoOrdenEstacionRequest estadoOrdenEstacionRequest) {
        EstadoOrdenEstacion estadoOrdenEstacion = estadoOrdenEstacionMapper.toEntity(estadoOrdenEstacionRequest);
        return estadoOrdenEstacionMapper.toResponse(estadoOrdenEstacionRepository.save(estadoOrdenEstacion));
    }

    @Override
    public void deleteById(Long id) {
        estadoOrdenEstacionRepository.deleteById(id);
    }
}
