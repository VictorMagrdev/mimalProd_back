package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.OrdenProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.OrdenProduccionResponse;

import java.util.List;

public interface OrdenProduccionService {

    List<OrdenProduccionResponse> getOrdenesProduccion();

    OrdenProduccionResponse getOrdenProduccionById(Long id);

    OrdenProduccionResponse createOrdenProduccion(OrdenProduccionRequest ordenProduccionRequest);

    OrdenProduccionResponse updateOrdenProduccion(Long id, OrdenProduccionRequest ordenProduccionRequest);

    void deleteOrdenProduccion(Long id);
}
