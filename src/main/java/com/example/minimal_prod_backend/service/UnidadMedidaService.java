package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.UnidadMedidaRequest;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;

import java.util.List;

public interface UnidadMedidaService {
    List<UnidadMedidaResponse> getUnidadesMedida();

    UnidadMedidaResponse getUnidadMedidaById(Long id);

    UnidadMedidaResponse createUnidadMedida(UnidadMedidaRequest unidadMedidaInput);

    UnidadMedidaResponse updateUnidadMedida(Long id, UnidadMedidaRequest unidadMedidaInput);

    void deleteUnidadMedida(Long id);
}
