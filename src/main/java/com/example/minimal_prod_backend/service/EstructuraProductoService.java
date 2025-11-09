package com.example.minimal_prod_backend.service;


import com.example.minimal_prod_backend.dto.Request.EstructuraProductoRequest;
import com.example.minimal_prod_backend.dto.Response.EstructuraProductoResponse;

import java.util.List;

public interface EstructuraProductoService {
    List<EstructuraProductoResponse> getAllEstructuras();

    EstructuraProductoResponse getEstructuraById(Long id);

    EstructuraProductoResponse createEstructura(EstructuraProductoRequest request);

    EstructuraProductoResponse updateEstructura(Long id, EstructuraProductoRequest request);

    void deleteEstructura(Long id);

    List<EstructuraProductoResponse> getEstructurasByProductoPadreId(Long productoPadreId);
}
