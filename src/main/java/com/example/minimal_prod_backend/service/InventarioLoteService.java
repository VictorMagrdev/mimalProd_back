package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.InventarioLoteInput;
import com.example.minimal_prod_backend.dto.InventarioLoteResponse;

import java.util.List;

public interface InventarioLoteService {
    List<InventarioLoteResponse> getInventarioLotes();
    InventarioLoteResponse getInventarioLoteById(Long id);
    InventarioLoteResponse createInventarioLote(InventarioLoteInput inventarioLoteInput);
    InventarioLoteResponse updateInventarioLote(Long id, InventarioLoteInput inventarioLoteInput);
    void deleteInventarioLote(Long id);
}
