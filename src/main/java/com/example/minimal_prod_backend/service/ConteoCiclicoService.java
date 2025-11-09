package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.ConteoCiclicoRequest;
import com.example.minimal_prod_backend.dto.Response.ConteoCiclicoResponse;

import java.util.List;

public interface ConteoCiclicoService {
    List<ConteoCiclicoResponse> getConteosCiclicos();

    ConteoCiclicoResponse getConteoCiclicoById(Long id);

    ConteoCiclicoResponse createConteoCiclico(ConteoCiclicoRequest input);

    ConteoCiclicoResponse updateConteoCiclico(Long id, ConteoCiclicoRequest input);

    boolean deleteConteoCiclico(Long id);
}
