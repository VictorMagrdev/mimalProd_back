package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.CostoOrdenRequest;
import com.example.minimal_prod_backend.dto.CostoOrdenResponse;

import java.util.List;

public interface CostoOrdenService {
    List<CostoOrdenResponse> getCostosOrden();

    CostoOrdenResponse getCostoOrdenById(Long id);

    CostoOrdenResponse createCostoOrden(CostoOrdenRequest costoOrdenInput);

    CostoOrdenResponse updateCostoOrden(Long id, CostoOrdenRequest costoOrdenInput);

    void deleteCostoOrden(Long id);
}
