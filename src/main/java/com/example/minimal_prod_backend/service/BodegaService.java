package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.BodegaRequest;
import com.example.minimal_prod_backend.dto.BodegaResponse;

import java.util.List;

public interface BodegaService {
    List<BodegaResponse> getBodegas();

    BodegaResponse getBodegaById(Long id);

    BodegaResponse createBodega(BodegaRequest bodegaInput);

    BodegaResponse updateBodega(Long id, BodegaRequest bodegaInput);

    void deleteBodega(Long id);
}
