package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.MaquinaRequest;
import com.example.minimal_prod_backend.dto.Response.MaquinaResponse;
import com.example.minimal_prod_backend.entity.Maquina;

import java.util.List;
import java.util.Optional;

public interface MaquinaService {
    List<MaquinaResponse> getMaquinas();

    MaquinaResponse getMaquinaById(Long id);

    MaquinaResponse createMaquina(MaquinaRequest request);

    MaquinaResponse updateMaquina(Long id, MaquinaRequest request);

    void deleteMaquina(Long id);

    Optional<Maquina> findById(Long id);
}
