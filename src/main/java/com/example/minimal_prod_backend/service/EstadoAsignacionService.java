package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.EstadoAsignacionRequest;
import com.example.minimal_prod_backend.dto.EstadoAsignacionResponse;

import java.util.List;

public interface EstadoAsignacionService {
    List<EstadoAsignacionResponse> getEstadosAsignacion();

    EstadoAsignacionResponse getEstadoAsignacionById(Long id);

    EstadoAsignacionResponse createEstadoAsignacion(EstadoAsignacionRequest request);

    EstadoAsignacionResponse updateEstadoAsignacion(Long id, EstadoAsignacionRequest request);

    void deleteEstadoAsignacion(Long id);
}
