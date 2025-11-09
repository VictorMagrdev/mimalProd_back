package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.TipoActividadRequest;
import com.example.minimal_prod_backend.dto.Response.TipoActividadResponse;

import java.util.List;

public interface TipoActividadService {
    List<TipoActividadResponse> findAll();

    TipoActividadResponse findById(Long id);

    TipoActividadResponse save(TipoActividadRequest tipoActividadRequest);

    void deleteById(Long id);
}
