package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.CostoOrden;
import com.example.minimal_prod_backend.repository.CostoOrdenRepository;
import com.example.minimal_prod_backend.service.CostoOrdenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostoOrdenServiceImpl implements CostoOrdenService {

    private final CostoOrdenRepository costoOrdenRepository;

    public CostoOrdenServiceImpl(CostoOrdenRepository costoOrdenRepository) {
        this.costoOrdenRepository = costoOrdenRepository;
    }

    @Override
    public List<CostoOrden> getCostosOrden() {
        return costoOrdenRepository.findAll();
    }

    @Override
    public CostoOrden getCostoOrdenById(Long id) {
        return costoOrdenRepository.findById(id).orElse(null);
    }

    @Override
    public CostoOrden createCostoOrden(CostoOrden costoOrden) {
        return costoOrdenRepository.save(costoOrden);
    }

    @Override
    public CostoOrden updateCostoOrden(Long id, CostoOrden costoOrden) {
        return costoOrdenRepository.findById(id).map(existingCostoOrden -> {
            existingCostoOrden.setOrden(costoOrden.getOrden());
            existingCostoOrden.setTipoCosto(costoOrden.getTipoCosto());
            existingCostoOrden.setDescripcion(costoOrden.getDescripcion());
            existingCostoOrden.setMonto(costoOrden.getMonto());
            existingCostoOrden.setMoneda(costoOrden.getMoneda());
            existingCostoOrden.setRegistradoEn(costoOrden.getRegistradoEn());
            return costoOrdenRepository.save(existingCostoOrden);
        }).orElse(null);
    }

    @Override
    public void deleteCostoOrden(Long id) {
        costoOrdenRepository.deleteById(id);
    }
}
