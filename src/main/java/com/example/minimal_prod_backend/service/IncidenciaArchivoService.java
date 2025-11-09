package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.IncidenciaArchivoRequest;
import com.example.minimal_prod_backend.dto.Response.IncidenciaArchivoResponse;

import java.util.List;

public interface IncidenciaArchivoService {
    IncidenciaArchivoResponse create(IncidenciaArchivoRequest request);

    List<IncidenciaArchivoResponse> findByIncidencia(Long incidenciaId);

    void delete(Long id);

    List<IncidenciaArchivoResponse> findAll();
}
