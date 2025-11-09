package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.MaquinaRequest;
import com.example.minimal_prod_backend.dto.Response.MaquinaResponse;

import java.util.List;

public interface MaquinaService {
    List<MaquinaResponse> getMaquinas();

    MaquinaResponse getMaquinaById(Long id);

    MaquinaResponse createMaquina(MaquinaRequest request);

    MaquinaResponse updateMaquina(Long id, MaquinaRequest request);

    void deleteMaquina(Long id);
}
