package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.ExcepcionTiempoRequest;
import com.example.minimal_prod_backend.dto.ExcepcionTiempoResponse;

import java.util.List;

public interface ExcepcionTiempoService {
    List<ExcepcionTiempoResponse> getExcepcionesTiempo();

    ExcepcionTiempoResponse getExcepcionTiempoById(Long id);

    ExcepcionTiempoResponse createExcepcionTiempo(ExcepcionTiempoRequest request);

    ExcepcionTiempoResponse updateExcepcionTiempo(Long id, ExcepcionTiempoRequest request);

    void deleteExcepcionTiempo(Long id);

    ExcepcionTiempoResponse resolveExcepcionTiempo(Long id);
}
