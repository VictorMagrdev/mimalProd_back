package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.EstadoOrden;

import java.util.List;

public interface EstadoOrdenService {
    List<EstadoOrden> getEstadosOrden();
    EstadoOrden getEstadoOrdenById(Long id);
    EstadoOrden createEstadoOrden(EstadoOrden estadoOrden);
    EstadoOrden updateEstadoOrden(Long id, EstadoOrden estadoOrden);
    void deleteEstadoOrden(Long id);
}
