package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.UnidadConversion;
import com.example.minimal_prod_backend.repository.UnidadConversionRepository;
import com.example.minimal_prod_backend.service.UnidadConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadConversionServiceImpl implements UnidadConversionService {

    private final UnidadConversionRepository unidadConversionRepository;

    public UnidadConversionServiceImpl(UnidadConversionRepository unidadConversionRepository) {
        this.unidadConversionRepository = unidadConversionRepository;
    }

    @Override
    public List<UnidadConversion> getUnidadConversiones() {
        return unidadConversionRepository.findAll();
    }

    @Override
    public UnidadConversion getUnidadConversionById(Long id) {
        return unidadConversionRepository.findById(id).orElse(null);
    }

    @Override
    public UnidadConversion createUnidadConversion(UnidadConversion unidadConversion) {
        return unidadConversionRepository.save(unidadConversion);
    }

    @Override
    public UnidadConversion updateUnidadConversion(Long id, UnidadConversion unidadConversion) {
        return unidadConversionRepository.findById(id).map(existingUnidadConversion -> {
            existingUnidadConversion.setOrigen(unidadConversion.getOrigen());
            existingUnidadConversion.setDestino(unidadConversion.getDestino());
            existingUnidadConversion.setFactor(unidadConversion.getFactor());
            return unidadConversionRepository.save(existingUnidadConversion);
        }).orElse(null);
    }

    @Override
    public void deleteUnidadConversion(Long id) {
        unidadConversionRepository.deleteById(id);
    }
}
