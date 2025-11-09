package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.DiscrepanciaInventarioRequest;
import com.example.minimal_prod_backend.dto.Response.DiscrepanciaInventarioResponse;

import java.util.List;

public interface DiscrepanciaInventarioService {
    List<DiscrepanciaInventarioResponse> getDiscrepanciasInventario();

    DiscrepanciaInventarioResponse getDiscrepanciaInventarioById(Long id);

    DiscrepanciaInventarioResponse createDiscrepanciaInventario(DiscrepanciaInventarioRequest discrepanciaInventarioInput);

    DiscrepanciaInventarioResponse updateDiscrepanciaInventario(Long id, DiscrepanciaInventarioRequest discrepanciaInventarioInput);

    void deleteDiscrepanciaInventario(Long id);
}
