package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.TipoCostoInput;
import com.example.minimal_prod_backend.dto.TipoCostoResponse;

import java.util.List;

public interface TipoCostoService {
    List<TipoCostoResponse> getTiposCosto();
    TipoCostoResponse getTipoCostoById(Integer id);
    TipoCostoResponse createTipoCosto(TipoCostoInput tipoCostoInput);
    TipoCostoResponse updateTipoCosto(Integer id, TipoCostoInput tipoCostoInput);
    void deleteTipoCosto(Integer id);
}
