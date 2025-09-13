package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.OrdenProduccionInput;
import com.example.minimal_prod_backend.dto.OrdenProduccionResponse;

import java.util.List;

public interface OrdenProduccionService {
    List<OrdenProduccionResponse> getOrdenesProduccion();
    OrdenProduccionResponse getOrdenProduccionById(Long id);
    OrdenProduccionResponse createOrdenProduccion(OrdenProduccionInput ordenProduccionInput);
    OrdenProduccionResponse updateOrdenProduccion(Long id, OrdenProduccionInput ordenProduccionInput);
    void deleteOrdenProduccion(Long id);
}
