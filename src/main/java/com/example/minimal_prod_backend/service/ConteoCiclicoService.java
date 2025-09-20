package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.ConteoCiclicoInput;
import com.example.minimal_prod_backend.dto.ConteoCiclicoResponse;

import java.util.List;

public interface ConteoCiclicoService {
    List<ConteoCiclicoResponse> getConteosCiclicos();

    ConteoCiclicoResponse getConteoCiclicoById(Long id);

    ConteoCiclicoResponse createConteoCiclico(ConteoCiclicoInput input);

    ConteoCiclicoResponse updateConteoCiclico(Long id, ConteoCiclicoInput input);

    boolean deleteConteoCiclico(Long id);
}
