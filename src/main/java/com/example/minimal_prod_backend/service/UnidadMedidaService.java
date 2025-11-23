package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.UnidadMedidaRequest;
import com.example.minimal_prod_backend.dto.Response.UnidadMedidaResponse;

import java.util.List;
import java.util.Optional;

public interface UnidadMedidaService {
    List<UnidadMedidaResponse> getUnidadesMedida();

    UnidadMedidaResponse getUnidadMedidaById(Long id);

    UnidadMedidaResponse createUnidadMedida(UnidadMedidaRequest unidadMedidaInput);

    UnidadMedidaResponse updateUnidadMedida(Long id, UnidadMedidaRequest unidadMedidaInput);

    void deleteUnidadMedida(Long id);

    Optional<UnidadMedidaResponse> findById(Long id);
}
