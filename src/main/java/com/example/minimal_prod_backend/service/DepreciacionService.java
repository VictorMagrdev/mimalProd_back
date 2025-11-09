package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.DepreciacionRequest;
import com.example.minimal_prod_backend.dto.Response.DepreciacionResponse;

import java.util.List;

public interface DepreciacionService {
    List<DepreciacionResponse> getDepreciaciones();

    DepreciacionResponse getDepreciacionById(Long id);

    DepreciacionResponse createDepreciacion(DepreciacionRequest request);

    DepreciacionResponse updateDepreciacion(Long id, DepreciacionRequest request);

    void deleteDepreciacion(Long id);

    void calcularDepreciacionMensual();

    void calcularDepreciacionAnual();
}
