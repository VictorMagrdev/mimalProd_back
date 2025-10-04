package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.EstadoOrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.EstadoOrdenTrabajoResponse;

import java.util.List;

public interface EstadoOrdenTrabajoService {
    List<EstadoOrdenTrabajoResponse> findAll();
    EstadoOrdenTrabajoResponse findById(Long id);
    EstadoOrdenTrabajoResponse save(EstadoOrdenTrabajoRequest estadoOrdenTrabajoRequest);
    void deleteById(Long id);
}
