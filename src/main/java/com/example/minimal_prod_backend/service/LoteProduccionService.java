package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.LoteProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.LoteProduccionResponse;

import java.util.List;

public interface LoteProduccionService {
    List<LoteProduccionResponse> getLotesProduccion();

    LoteProduccionResponse getLoteProduccionById(Long id);

    LoteProduccionResponse createLoteProduccion(LoteProduccionRequest loteProduccionInput);

    LoteProduccionResponse updateLoteProduccion(Long id, LoteProduccionRequest loteProduccionInput);

    void deleteLoteProduccion(Long id);
}
