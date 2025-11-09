package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.RegistroTiempoRequest;
import com.example.minimal_prod_backend.dto.Response.RegistroTiempoResponse;

import java.util.List;

public interface RegistroTiempoService {
    List<RegistroTiempoResponse> getRegistrosTiempo();

    RegistroTiempoResponse getRegistroTiempoById(Long id);

    RegistroTiempoResponse createRegistroTiempo(RegistroTiempoRequest request);

    RegistroTiempoResponse updateRegistroTiempo(Long id, RegistroTiempoRequest request);

    void deleteRegistroTiempo(Long id);
}
