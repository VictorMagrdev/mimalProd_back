package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.InventarioLoteRequest;
import com.example.minimal_prod_backend.dto.Response.InventarioLoteResponse;

import java.util.List;

public interface InventarioLoteService {
    List<InventarioLoteResponse> getInventarioLotes();

    InventarioLoteResponse getInventarioLoteById(Long id);

    InventarioLoteResponse createInventarioLote(InventarioLoteRequest inventarioLoteInput);

    InventarioLoteResponse updateInventarioLote(Long id, InventarioLoteRequest inventarioLoteInput);

    void deleteInventarioLote(Long id);
}
