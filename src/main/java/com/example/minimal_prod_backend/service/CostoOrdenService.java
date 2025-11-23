package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.CostoOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.CostoOrdenResponse;

import java.util.List;

public interface CostoOrdenService {
    List<CostoOrdenResponse> getCostosOrden();

    CostoOrdenResponse getCostoOrdenById(Long id);

    CostoOrdenResponse createCostoOrden(CostoOrdenRequest costoOrdenInput);

    CostoOrdenResponse updateCostoOrden(Long id, CostoOrdenRequest costoOrdenInput);

    void deleteCostoOrden(Long id);

    List<CostoOrdenResponse> findByOrdenProduccion(Long id);
}
