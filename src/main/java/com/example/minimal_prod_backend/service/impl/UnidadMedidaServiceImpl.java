package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.service.UnidadMedidaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    private final UnidadMedidaRepository unidadMedidaRepository;

    public UnidadMedidaServiceImpl(UnidadMedidaRepository unidadMedidaRepository) {
        this.unidadMedidaRepository = unidadMedidaRepository;
    }

    @Override
    public List<UnidadMedida> getUnidadesMedida() {
        return unidadMedidaRepository.findAll();
    }

    @Override
    public UnidadMedida getUnidadMedidaById(Long id) {
        return unidadMedidaRepository.findById(id).orElse(null);
    }

    @Override
    public UnidadMedida createUnidadMedida(UnidadMedida unidadMedida) {
        return unidadMedidaRepository.save(unidadMedida);
    }

    @Override
    public UnidadMedida updateUnidadMedida(Long id, UnidadMedida unidadMedida) {
        return unidadMedidaRepository.findById(id).map(existingUnidadMedida -> {
            existingUnidadMedida.setCodigo(unidadMedida.getCodigo());
            existingUnidadMedida.setNombre(unidadMedida.getNombre());
            existingUnidadMedida.setAbreviatura(unidadMedida.getAbreviatura());
            existingUnidadMedida.setTipo(unidadMedida.getTipo());
            existingUnidadMedida.setEsActiva(unidadMedida.isEsActiva());
            existingUnidadMedida.setEsBase(unidadMedida.isEsBase());
            return unidadMedidaRepository.save(existingUnidadMedida);
        }).orElse(null);
    }

    @Override
    public void deleteUnidadMedida(Long id) {
        unidadMedidaRepository.deleteById(id);
    }
}
