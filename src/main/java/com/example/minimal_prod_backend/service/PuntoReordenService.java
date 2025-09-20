package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.PuntoReordenInput;
import com.example.minimal_prod_backend.dto.PuntoReordenResponse;

import java.util.List;

public interface PuntoReordenService {
    List<PuntoReordenResponse> getPuntosReorden();

    PuntoReordenResponse getPuntoReordenById(Long id);

    PuntoReordenResponse createPuntoReorden(PuntoReordenInput puntoReordenInput);

    PuntoReordenResponse updatePuntoReorden(Long id, PuntoReordenInput puntoReordenInput);

    void deletePuntoReorden(Long id);
}
