package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.EstadoOrden;
import com.example.minimal_prod_backend.repository.EstadoOrdenRepository;
import com.example.minimal_prod_backend.service.EstadoOrdenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoOrdenServiceImpl implements EstadoOrdenService {

    private final EstadoOrdenRepository estadoOrdenRepository;

    public EstadoOrdenServiceImpl(EstadoOrdenRepository estadoOrdenRepository) {
        this.estadoOrdenRepository = estadoOrdenRepository;
    }

    @Override
    public List<EstadoOrden> getEstadosOrden() {
        return estadoOrdenRepository.findAll();
    }

    @Override
    public EstadoOrden getEstadoOrdenById(Long id) {
        return estadoOrdenRepository.findById(id).orElse(null);
    }

    @Override
    public EstadoOrden createEstadoOrden(EstadoOrden estadoOrden) {
        return estadoOrdenRepository.save(estadoOrden);
    }

    @Override
    public EstadoOrden updateEstadoOrden(Long id, EstadoOrden estadoOrden) {
        return estadoOrdenRepository.findById(id).map(existingEstadoOrden -> {
            existingEstadoOrden.setCodigo(estadoOrden.getCodigo());
            existingEstadoOrden.setNombre(estadoOrden.getNombre());
            existingEstadoOrden.setDescripcion(estadoOrden.getDescripcion());
            existingEstadoOrden.setActivo(estadoOrden.isActivo());
            return estadoOrdenRepository.save(existingEstadoOrden);
        }).orElse(null);
    }

    @Override
    public void deleteEstadoOrden(Long id) {
        estadoOrdenRepository.deleteById(id);
    }
}
