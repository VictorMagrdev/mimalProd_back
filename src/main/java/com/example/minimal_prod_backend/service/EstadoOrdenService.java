package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.EstadoOrdenInput;
import com.example.minimal_prod_backend.dto.EstadoOrdenResponse;

import java.util.List;

public interface EstadoOrdenService {
    List<EstadoOrdenResponse> getEstadosOrden();
    EstadoOrdenResponse getEstadoOrdenById(Integer id);
    EstadoOrdenResponse createEstadoOrden(EstadoOrdenInput estadoOrdenInput);
    EstadoOrdenResponse updateEstadoOrden(Integer id, EstadoOrdenInput estadoOrdenInput);
    void deleteEstadoOrden(Integer id);
}
