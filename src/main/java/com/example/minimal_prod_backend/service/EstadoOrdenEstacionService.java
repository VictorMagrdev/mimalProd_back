package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.EstadoOrdenEstacionRequest;
import com.example.minimal_prod_backend.dto.EstadoOrdenEstacionResponse;

import java.util.List;

public interface EstadoOrdenEstacionService {
    List<EstadoOrdenEstacionResponse> findAll();
    EstadoOrdenEstacionResponse findById(Long id);
    EstadoOrdenEstacionResponse save(EstadoOrdenEstacionRequest estadoOrdenEstacionRequest);
    void deleteById(Long id);
}
