package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.PuntoReordenRequest;
import com.example.minimal_prod_backend.dto.PuntoReordenResponse;

import java.util.List;

public interface PuntoReordenService {
    List<PuntoReordenResponse> getPuntosReorden();

    PuntoReordenResponse getPuntoReordenById(Long id);

    PuntoReordenResponse createPuntoReorden(PuntoReordenRequest puntoReordenInput);

    PuntoReordenResponse updatePuntoReorden(Long id, PuntoReordenRequest puntoReordenInput);

    void deletePuntoReorden(Long id);
}
