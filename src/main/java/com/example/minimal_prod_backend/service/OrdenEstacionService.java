package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.OrdenEstacionRequest;
import com.example.minimal_prod_backend.dto.OrdenEstacionResponse;

import java.util.List;

public interface OrdenEstacionService {
    List<OrdenEstacionResponse> getOrdenesEstacion();

    OrdenEstacionResponse getOrdenEstacionById(Long id);

    OrdenEstacionResponse createOrdenEstacion(OrdenEstacionRequest ordenEstacionInput);

    OrdenEstacionResponse updateOrdenEstacion(Long id, OrdenEstacionRequest ordenEstacionInput);

    void deleteOrdenEstacion(Long id);
}
