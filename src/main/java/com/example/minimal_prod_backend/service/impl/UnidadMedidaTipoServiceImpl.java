package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import com.example.minimal_prod_backend.repository.UnidadMedidaTipoRepository;
import com.example.minimal_prod_backend.service.UnidadMedidaTipoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaTipoServiceImpl implements UnidadMedidaTipoService {

    private final UnidadMedidaTipoRepository unidadMedidaTipoRepository;

    public UnidadMedidaTipoServiceImpl(UnidadMedidaTipoRepository unidadMedidaTipoRepository) {
        this.unidadMedidaTipoRepository = unidadMedidaTipoRepository;
    }

    @Override
    public List<UnidadMedidaTipo> getUnidadMedidaTipos() {
        return unidadMedidaTipoRepository.findAll();
    }

    @Override
    public UnidadMedidaTipo getUnidadMedidaTipoById(Long id) {
        return unidadMedidaTipoRepository.findById(id).orElse(null);
    }

    @Override
    public UnidadMedidaTipo createUnidadMedidaTipo(UnidadMedidaTipo unidadMedidaTipo) {
        return unidadMedidaTipoRepository.save(unidadMedidaTipo);
    }

    @Override
    public UnidadMedidaTipo updateUnidadMedidaTipo(Long id, UnidadMedidaTipo unidadMedidaTipo) {
        return unidadMedidaTipoRepository.findById(id).map(existingUnidadMedidaTipo -> {
            existingUnidadMedidaTipo.setCodigo(unidadMedidaTipo.getCodigo());
            existingUnidadMedidaTipo.setNombre(unidadMedidaTipo.getNombre());
            existingUnidadMedidaTipo.setDescripcion(unidadMedidaTipo.getDescripcion());
            return unidadMedidaTipoRepository.save(existingUnidadMedidaTipo);
        }).orElse(null);
    }

    @Override
    public void deleteUnidadMedidaTipo(Long id) {
        unidadMedidaTipoRepository.deleteById(id);
    }
}
