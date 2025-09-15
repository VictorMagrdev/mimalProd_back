package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.TipoBodegaInput;
import com.example.minimal_prod_backend.dto.TipoBodegaResponse;

import java.util.List;

public interface TipoBodegaService {
    List<TipoBodegaResponse> getTiposBodega();
    TipoBodegaResponse getTipoBodegaById(Long id);
    TipoBodegaResponse createTipoBodega(TipoBodegaInput tipoBodegaInput);
    TipoBodegaResponse updateTipoBodega(Long id, TipoBodegaInput tipoBodegaInput);
    void deleteTipoBodega(Long id);
}
