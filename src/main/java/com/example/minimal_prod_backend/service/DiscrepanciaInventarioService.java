package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioInput;
import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioResponse;

import java.util.List;

public interface DiscrepanciaInventarioService {
    List<DiscrepanciaInventarioResponse> getDiscrepanciasInventario();

    DiscrepanciaInventarioResponse getDiscrepanciaInventarioById(Long id);

    DiscrepanciaInventarioResponse createDiscrepanciaInventario(DiscrepanciaInventarioInput discrepanciaInventarioInput);

    DiscrepanciaInventarioResponse updateDiscrepanciaInventario(Long id, DiscrepanciaInventarioInput discrepanciaInventarioInput);

    void deleteDiscrepanciaInventario(Long id);
}
