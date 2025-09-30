package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.RegistroTiempoRequest;
import com.example.minimal_prod_backend.dto.RegistroTiempoResponse;

import java.util.List;

public interface RegistroTiempoService {
    List<RegistroTiempoResponse> getRegistrosTiempo();

    RegistroTiempoResponse getRegistroTiempoById(Long id);

    RegistroTiempoResponse createRegistroTiempo(RegistroTiempoRequest request);

    RegistroTiempoResponse updateRegistroTiempo(Long id, RegistroTiempoRequest request);

    void deleteRegistroTiempo(Long id);
}
