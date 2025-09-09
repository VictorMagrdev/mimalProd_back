package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.LoteProduccion;

import java.util.List;

public interface LoteProduccionService {
    List<LoteProduccion> getLotesProduccion();
    LoteProduccion getLoteProduccionById(Long id);
    LoteProduccion createLoteProduccion(LoteProduccion loteProduccion);
    LoteProduccion updateLoteProduccion(Long id, LoteProduccion loteProduccion);
    void deleteLoteProduccion(Long id);
}
