package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.OperacionRutaRequest;
import com.example.minimal_prod_backend.dto.OperacionRutaResponse;

import java.util.List;

public interface OperacionRutaService {
    List<OperacionRutaResponse> findAll();
    OperacionRutaResponse findById(Long id);
    OperacionRutaResponse save(OperacionRutaRequest operacionRutaRequest);
    void deleteById(Long id);
}
