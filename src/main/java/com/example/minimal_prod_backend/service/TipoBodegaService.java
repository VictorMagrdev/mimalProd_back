package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.TipoBodegaRequest;
import com.example.minimal_prod_backend.dto.TipoBodegaResponse;

import java.util.List;

public interface TipoBodegaService {
    List<TipoBodegaResponse> getTiposBodega();

    TipoBodegaResponse getTipoBodegaById(Long id);

    TipoBodegaResponse createTipoBodega(TipoBodegaRequest tipoBodegaInput);

    TipoBodegaResponse updateTipoBodega(Long id, TipoBodegaRequest tipoBodegaInput);

    void deleteTipoBodega(Long id);
}
