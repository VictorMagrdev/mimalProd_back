package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.TipoCostoRequest;
import com.example.minimal_prod_backend.dto.Response.TipoCostoResponse;
import com.example.minimal_prod_backend.entity.TipoCosto;

import java.util.List;
import java.util.Optional;

public interface TipoCostoService {
    List<TipoCostoResponse> getTiposCosto();

    TipoCostoResponse getTipoCostoById(Long id);

    TipoCostoResponse createTipoCosto(TipoCostoRequest tipoCostoInput);

    TipoCostoResponse updateTipoCosto(Long id, TipoCostoRequest tipoCostoInput);

    void deleteTipoCosto(Long id);

    Optional<TipoCostoResponse> findById(Long id);
}
