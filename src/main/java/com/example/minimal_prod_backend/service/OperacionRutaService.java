package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.OperacionRutaRequest;
import com.example.minimal_prod_backend.dto.Response.OperacionRutaResponse;

import java.util.List;

public interface OperacionRutaService {
    List<OperacionRutaResponse> findAll();

    OperacionRutaResponse findById(Long id);

    OperacionRutaResponse save(OperacionRutaRequest operacionRutaRequest);

    void deleteById(Long id);
}
