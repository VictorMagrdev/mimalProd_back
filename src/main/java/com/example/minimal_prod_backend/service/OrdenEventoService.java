package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.OrdenEventoRequest;
import com.example.minimal_prod_backend.dto.Response.OrdenEventoResponse;

import java.util.List;

public interface OrdenEventoService {
    List<OrdenEventoResponse> getOrdenesEvento();

    OrdenEventoResponse getOrdenEventoById(Long id);

    OrdenEventoResponse createOrdenEvento(OrdenEventoRequest ordenEventoInput);

    OrdenEventoResponse updateOrdenEvento(Long id, OrdenEventoRequest ordenEventoInput);

    void deleteOrdenEvento(Long id);
}
