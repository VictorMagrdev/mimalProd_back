package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.service.OrdenProduccionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenProduccionServiceImpl implements OrdenProduccionService {

    private final OrdenProduccionRepository ordenProduccionRepository;

    public OrdenProduccionServiceImpl(OrdenProduccionRepository ordenProduccionRepository) {
        this.ordenProduccionRepository = ordenProduccionRepository;
    }

    @Override
    public List<OrdenProduccion> getOrdenesProduccion() {
        return ordenProduccionRepository.findAll();
    }

    @Override
    public OrdenProduccion getOrdenProduccionById(Long id) {
        return ordenProduccionRepository.findById(id).orElse(null);
    }

    @Override
    public OrdenProduccion createOrdenProduccion(OrdenProduccion ordenProduccion) {
        return ordenProduccionRepository.save(ordenProduccion);
    }

    @Override
    public OrdenProduccion updateOrdenProduccion(Long id, OrdenProduccion ordenProduccion) {
        return ordenProduccionRepository.findById(id).map(existingOrdenProduccion -> {
            existingOrdenProduccion.setNumeroOrden(ordenProduccion.getNumeroOrden());
            existingOrdenProduccion.setLote(ordenProduccion.getLote());
            existingOrdenProduccion.setProducto(ordenProduccion.getProducto());
            existingOrdenProduccion.setCantidad(ordenProduccion.getCantidad());
            existingOrdenProduccion.setUnidad(ordenProduccion.getUnidad());
            existingOrdenProduccion.setEstado(ordenProduccion.getEstado());
            existingOrdenProduccion.setInicioPlanificado(ordenProduccion.getInicioPlanificado());
            existingOrdenProduccion.setFinPlanificado(ordenProduccion.getFinPlanificado());
            existingOrdenProduccion.setInicioReal(ordenProduccion.getInicioReal());
            existingOrdenProduccion.setFinReal(ordenProduccion.getFinReal());
            existingOrdenProduccion.setCantidadDesperdicio(ordenProduccion.getCantidadDesperdicio());
            existingOrdenProduccion.setCantidadProducida(ordenProduccion.getCantidadProducida());
            existingOrdenProduccion.setCreadoPor(ordenProduccion.getCreadoPor());
            existingOrdenProduccion.setObservaciones(ordenProduccion.getObservaciones());
            existingOrdenProduccion.setActualizadoEn(ordenProduccion.getActualizadoEn());
            return ordenProduccionRepository.save(existingOrdenProduccion);
        }).orElse(null);
    }

    @Override
    public void deleteOrdenProduccion(Long id) {
        ordenProduccionRepository.deleteById(id);
    }
}
