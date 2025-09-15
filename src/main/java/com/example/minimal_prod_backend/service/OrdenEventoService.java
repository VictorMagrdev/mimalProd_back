package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.OrdenEventoInput;
import com.example.minimal_prod_backend.dto.OrdenEventoResponse;

import java.util.List;

public interface OrdenEventoService {
    List<OrdenEventoResponse> getOrdenesEvento();
    OrdenEventoResponse getOrdenEventoById(Long id);
    OrdenEventoResponse createOrdenEvento(OrdenEventoInput ordenEventoInput);
    OrdenEventoResponse updateOrdenEvento(Long id, OrdenEventoInput ordenEventoInput);
    void deleteOrdenEvento(Long id);
}
