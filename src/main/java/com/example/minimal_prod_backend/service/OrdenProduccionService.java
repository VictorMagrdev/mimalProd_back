package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.OrdenProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.OrdenProduccionResponse;
import com.example.minimal_prod_backend.entity.OrdenProduccion;

import java.util.List;
import java.util.Optional;

public interface OrdenProduccionService {

    List<OrdenProduccionResponse> getOrdenesProduccion();

    OrdenProduccionResponse getOrdenProduccionById(Long id);

    OrdenProduccionResponse createOrdenProduccion(OrdenProduccionRequest ordenProduccionRequest);

    OrdenProduccionResponse updateOrdenProduccion(Long id, OrdenProduccionRequest ordenProduccionRequest);

    void deleteOrdenProduccion(Long id);

    Optional<OrdenProduccionResponse> findById(Long id);
}
