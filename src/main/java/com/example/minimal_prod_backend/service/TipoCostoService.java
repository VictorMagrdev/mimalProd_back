package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.TipoCostoInput;
import com.example.minimal_prod_backend.dto.TipoCostoResponse;

import java.util.List;

public interface TipoCostoService {
    List<TipoCostoResponse> getTiposCosto();
    TipoCostoResponse getTipoCostoById(Long id);
    TipoCostoResponse createTipoCosto(TipoCostoInput tipoCostoInput);
    TipoCostoResponse updateTipoCosto(Long id, TipoCostoInput tipoCostoInput);
    void deleteTipoCosto(Long id);
}
