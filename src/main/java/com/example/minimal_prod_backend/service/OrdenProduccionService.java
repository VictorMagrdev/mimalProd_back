package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.OrdenProduccion;

import java.util.List;

public interface OrdenProduccionService {
    List<OrdenProduccion> getOrdenesProduccion();
    OrdenProduccion getOrdenProduccionById(Long id);
    OrdenProduccion createOrdenProduccion(OrdenProduccion ordenProduccion);
    OrdenProduccion updateOrdenProduccion(Long id, OrdenProduccion ordenProduccion);
    void deleteOrdenProduccion(Long id);
}
