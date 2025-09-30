package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.AsignacionRequest;
import com.example.minimal_prod_backend.dto.AsignacionResponse;

import java.util.List;

public interface AsignacionService {
    List<AsignacionResponse> getAsignaciones();

    AsignacionResponse getAsignacionById(Long id);

    AsignacionResponse createAsignacion(AsignacionRequest request);

    AsignacionResponse updateAsignacion(Long id, AsignacionRequest request);

    void deleteAsignacion(Long id);
}
