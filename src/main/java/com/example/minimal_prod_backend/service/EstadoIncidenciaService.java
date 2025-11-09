package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.EstadoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoIncidenciaResponse;

import java.util.List;

public interface EstadoIncidenciaService {
    EstadoIncidenciaResponse create(EstadoIncidenciaRequest request);

    List<EstadoIncidenciaResponse> findAll();

    EstadoIncidenciaResponse findById(Long id);

    void delete(Long id);
}
