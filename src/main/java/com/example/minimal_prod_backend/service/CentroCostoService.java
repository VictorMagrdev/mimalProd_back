package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.CentroCostoInput;
import com.example.minimal_prod_backend.dto.CentroCostoResponse;

import java.util.List;

public interface CentroCostoService {
    List<CentroCostoResponse> getCentrosDeCosto();

    CentroCostoResponse getCentroDeCostoById(Long id);

    CentroCostoResponse createCentroDeCosto(CentroCostoInput centroCostoInput);

    CentroCostoResponse updateCentroDeCosto(Long id, CentroCostoInput centroCostoInput);

    void deleteCentroDeCosto(Long id);
}
