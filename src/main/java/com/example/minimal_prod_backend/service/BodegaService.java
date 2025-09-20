package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.BodegaInput;
import com.example.minimal_prod_backend.dto.BodegaResponse;

import java.util.List;

public interface BodegaService {
    List<BodegaResponse> getBodegas();

    BodegaResponse getBodegaById(Long id);

    BodegaResponse createBodega(BodegaInput bodegaInput);

    BodegaResponse updateBodega(Long id, BodegaInput bodegaInput);

    void deleteBodega(Long id);
}
