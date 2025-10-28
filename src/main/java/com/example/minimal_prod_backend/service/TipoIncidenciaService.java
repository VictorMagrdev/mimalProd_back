package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.TipoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.TipoIncidenciaResponse;

import java.util.List;

public interface TipoIncidenciaService {
    TipoIncidenciaResponse create(TipoIncidenciaRequest request);
    List<TipoIncidenciaResponse> findAll();
    TipoIncidenciaResponse findById(Long id);
    void delete(Long id);
}
