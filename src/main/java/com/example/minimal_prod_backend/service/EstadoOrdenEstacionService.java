package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.EstadoOrdenEstacionRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoOrdenEstacionResponse;

import java.util.List;

public interface EstadoOrdenEstacionService {
    List<EstadoOrdenEstacionResponse> findAll();

    EstadoOrdenEstacionResponse findById(Long id);

    EstadoOrdenEstacionResponse save(EstadoOrdenEstacionRequest estadoOrdenEstacionRequest);

    void deleteById(Long id);
}
