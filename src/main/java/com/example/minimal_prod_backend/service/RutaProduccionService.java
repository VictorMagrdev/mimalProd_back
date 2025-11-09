package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.RutaProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.RutaProduccionResponse;

import java.util.List;

public interface RutaProduccionService {
    List<RutaProduccionResponse> findAll();

    RutaProduccionResponse findById(Long id);

    RutaProduccionResponse save(RutaProduccionRequest rutaProduccionRequest);

    void deleteById(Long id);
}
