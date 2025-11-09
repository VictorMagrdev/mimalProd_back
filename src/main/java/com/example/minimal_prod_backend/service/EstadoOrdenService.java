package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.EstadoOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoOrdenResponse;

import java.util.List;

public interface EstadoOrdenService {
    List<EstadoOrdenResponse> getEstadosOrden();

    EstadoOrdenResponse getEstadoOrdenById(Long id);

    EstadoOrdenResponse createEstadoOrden(EstadoOrdenRequest estadoOrdenInput);

    EstadoOrdenResponse updateEstadoOrden(Long id, EstadoOrdenRequest estadoOrdenInput);

    void deleteEstadoOrden(Long id);
}
