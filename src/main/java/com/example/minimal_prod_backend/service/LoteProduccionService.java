package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.LoteProduccionInput;
import com.example.minimal_prod_backend.dto.LoteProduccionResponse;

import java.util.List;

public interface LoteProduccionService {
    List<LoteProduccionResponse> getLotesProduccion();
    LoteProduccionResponse getLoteProduccionById(Integer id);
    LoteProduccionResponse createLoteProduccion(LoteProduccionInput loteProduccionInput);
    LoteProduccionResponse updateLoteProduccion(Integer id, LoteProduccionInput loteProduccionInput);
    void deleteLoteProduccion(Integer id);
}
