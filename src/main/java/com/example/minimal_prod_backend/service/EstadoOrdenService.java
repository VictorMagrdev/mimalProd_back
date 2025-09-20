package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.EstadoOrdenInput;
import com.example.minimal_prod_backend.dto.EstadoOrdenResponse;

import java.util.List;

public interface EstadoOrdenService {
    List<EstadoOrdenResponse> getEstadosOrden();

    EstadoOrdenResponse getEstadoOrdenById(Long id);

    EstadoOrdenResponse createEstadoOrden(EstadoOrdenInput estadoOrdenInput);

    EstadoOrdenResponse updateEstadoOrden(Long id, EstadoOrdenInput estadoOrdenInput);

    void deleteEstadoOrden(Long id);
}
