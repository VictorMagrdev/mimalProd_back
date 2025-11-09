package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.EstadoAprobacionRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoAprobacionResponse;

import java.util.List;

public interface EstadoAprobacionService {
    List<EstadoAprobacionResponse> getEstadosAprobacion();

    EstadoAprobacionResponse getEstadoAprobacionById(Long id);

    EstadoAprobacionResponse createEstadoAprobacion(EstadoAprobacionRequest request);

    EstadoAprobacionResponse updateEstadoAprobacion(Long id, EstadoAprobacionRequest request);

    void deleteEstadoAprobacion(Long id);
}
