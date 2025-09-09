package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.TipoCosto;

import java.util.List;

public interface TipoCostoService {
    List<TipoCosto> getTiposCosto();
    TipoCosto getTipoCostoById(Long id);
    TipoCosto createTipoCosto(TipoCosto tipoCosto);
    TipoCosto updateTipoCosto(Long id, TipoCosto tipoCosto);
    void deleteTipoCosto(Long id);
}
