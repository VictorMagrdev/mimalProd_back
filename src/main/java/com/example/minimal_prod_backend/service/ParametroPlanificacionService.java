package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.ParametroPlanificacionRequest;
import com.example.minimal_prod_backend.dto.ParametroPlanificacionResponse;

import java.util.List;

public interface ParametroPlanificacionService {
    List<ParametroPlanificacionResponse> getAllParametros();

    ParametroPlanificacionResponse getParametroById(Long id);

    ParametroPlanificacionResponse createParametro(ParametroPlanificacionRequest request);

    ParametroPlanificacionResponse updateParametro(Long id, ParametroPlanificacionRequest request);

    ParametroPlanificacionResponse getParametroByProductoId(Long productoId);

    void deleteParametro(Long id);
}