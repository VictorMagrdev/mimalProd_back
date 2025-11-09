package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.SeguimientoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.Response.SeguimientoIncidenciaResponse;

import java.util.List;

public interface SeguimientoIncidenciaService {
    SeguimientoIncidenciaResponse create(SeguimientoIncidenciaRequest request);

    List<SeguimientoIncidenciaResponse> findByIncidencia(Long incidenciaId);

    void delete(Long id);
}
