package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.TipoCostoRequest;
import com.example.minimal_prod_backend.dto.Response.TipoCostoResponse;

import java.util.List;

public interface TipoCostoService {
    List<TipoCostoResponse> getTiposCosto();

    TipoCostoResponse getTipoCostoById(Long id);

    TipoCostoResponse createTipoCosto(TipoCostoRequest tipoCostoInput);

    TipoCostoResponse updateTipoCosto(Long id, TipoCostoRequest tipoCostoInput);

    void deleteTipoCosto(Long id);
}
