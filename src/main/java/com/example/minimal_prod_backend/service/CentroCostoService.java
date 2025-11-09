package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.CentroCostoRequest;
import com.example.minimal_prod_backend.dto.Response.CentroCostoResponse;

import java.util.List;

public interface CentroCostoService {
    List<CentroCostoResponse> getCentrosDeCosto();

    CentroCostoResponse getCentroDeCostoById(Long id);

    CentroCostoResponse createCentroDeCosto(CentroCostoRequest centroCostoInput);

    CentroCostoResponse updateCentroDeCosto(Long id, CentroCostoRequest centroCostoInput);

    void deleteCentroDeCosto(Long id);
}
