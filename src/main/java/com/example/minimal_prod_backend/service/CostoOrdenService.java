package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.CostoOrdenInput;
import com.example.minimal_prod_backend.dto.CostoOrdenResponse;

import java.util.List;

public interface CostoOrdenService {
    List<CostoOrdenResponse> getCostosOrden();

    CostoOrdenResponse getCostoOrdenById(Long id);

    CostoOrdenResponse createCostoOrden(CostoOrdenInput costoOrdenInput);

    CostoOrdenResponse updateCostoOrden(Long id, CostoOrdenInput costoOrdenInput);

    void deleteCostoOrden(Long id);
}
