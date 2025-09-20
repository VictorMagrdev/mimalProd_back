package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.OrdenEstacionInput;
import com.example.minimal_prod_backend.dto.OrdenEstacionResponse;

import java.util.List;

public interface OrdenEstacionService {
    List<OrdenEstacionResponse> getOrdenesEstacion();

    OrdenEstacionResponse getOrdenEstacionById(Long id);

    OrdenEstacionResponse createOrdenEstacion(OrdenEstacionInput ordenEstacionInput);

    OrdenEstacionResponse updateOrdenEstacion(Long id, OrdenEstacionInput ordenEstacionInput);

    void deleteOrdenEstacion(Long id);
}
