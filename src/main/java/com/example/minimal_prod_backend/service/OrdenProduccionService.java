package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.OrdenProduccionRequest;
import com.example.minimal_prod_backend.dto.OrdenProduccionResponse;

import java.util.List;

public interface OrdenProduccionService {

    List<OrdenProduccionResponse> getOrdenesProduccion();

    OrdenProduccionResponse getOrdenProduccionById(Long id);

    OrdenProduccionResponse createOrdenProduccion(OrdenProduccionRequest ordenProduccionRequest);

    OrdenProduccionResponse updateOrdenProduccion(Long id, OrdenProduccionRequest ordenProduccionRequest);

    void deleteOrdenProduccion(Long id);
}
