package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.LoteProduccion;
import com.example.minimal_prod_backend.repository.LoteProduccionRepository;
import com.example.minimal_prod_backend.service.LoteProduccionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoteProduccionServiceImpl implements LoteProduccionService {

    private final LoteProduccionRepository loteProduccionRepository;

    public LoteProduccionServiceImpl(LoteProduccionRepository loteProduccionRepository) {
        this.loteProduccionRepository = loteProduccionRepository;
    }

    @Override
    public List<LoteProduccion> getLotesProduccion() {
        return loteProduccionRepository.findAll();
    }

    @Override
    public LoteProduccion getLoteProduccionById(Long id) {
        return loteProduccionRepository.findById(id).orElse(null);
    }

    @Override
    public LoteProduccion createLoteProduccion(LoteProduccion loteProduccion) {
        return loteProduccionRepository.save(loteProduccion);
    }

    @Override
    public LoteProduccion updateLoteProduccion(Long id, LoteProduccion loteProduccion) {
        return loteProduccionRepository.findById(id).map(existingLoteProduccion -> {
            existingLoteProduccion.setNumeroLote(loteProduccion.getNumeroLote());
            existingLoteProduccion.setProducto(loteProduccion.getProducto());
            existingLoteProduccion.setFabricadoEn(loteProduccion.getFabricadoEn());
            existingLoteProduccion.setVenceEn(loteProduccion.getVenceEn());
            return loteProduccionRepository.save(existingLoteProduccion);
        }).orElse(null);
    }

    @Override
    public void deleteLoteProduccion(Long id) {
        loteProduccionRepository.deleteById(id);
    }
}
