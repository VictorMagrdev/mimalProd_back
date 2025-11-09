package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.IncidenciaRequest;
import com.example.minimal_prod_backend.dto.Response.IncidenciaResponse;

import java.util.List;

public interface IncidenciaService {
    IncidenciaResponse create(IncidenciaRequest request);

    List<IncidenciaResponse> findAll();

    IncidenciaResponse findById(Long id);

    void delete(Long id);
}
