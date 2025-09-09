package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.TipoCosto;
import com.example.minimal_prod_backend.repository.TipoCostoRepository;
import com.example.minimal_prod_backend.service.TipoCostoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoCostoServiceImpl implements TipoCostoService {

    private final TipoCostoRepository tipoCostoRepository;

    public TipoCostoServiceImpl(TipoCostoRepository tipoCostoRepository) {
        this.tipoCostoRepository = tipoCostoRepository;
    }

    @Override
    public List<TipoCosto> getTiposCosto() {
        return tipoCostoRepository.findAll();
    }

    @Override
    public TipoCosto getTipoCostoById(Long id) {
        return tipoCostoRepository.findById(id).orElse(null);
    }

    @Override
    public TipoCosto createTipoCosto(TipoCosto tipoCosto) {
        return tipoCostoRepository.save(tipoCosto);
    }

    @Override
    public TipoCosto updateTipoCosto(Long id, TipoCosto tipoCosto) {
        return tipoCostoRepository.findById(id).map(existingTipoCosto -> {
            existingTipoCosto.setCodigo(tipoCosto.getCodigo());
            existingTipoCosto.setNombre(tipoCosto.getNombre());
            existingTipoCosto.setDescripcion(tipoCosto.getDescripcion());
            existingTipoCosto.setActivo(tipoCosto.isActivo());
            return tipoCostoRepository.save(existingTipoCosto);
        }).orElse(null);
    }

    @Override
    public void deleteTipoCosto(Long id) {
        tipoCostoRepository.deleteById(id);
    }
}
