package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.UnidadMedidaInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;

import java.util.List;

public interface UnidadMedidaService {
    List<UnidadMedidaResponse> getUnidadesMedida();
    UnidadMedidaResponse getUnidadMedidaById(Integer id);
    UnidadMedidaResponse createUnidadMedida(UnidadMedidaInput unidadMedidaInput);
    UnidadMedidaResponse updateUnidadMedida(Integer id, UnidadMedidaInput unidadMedidaInput);
    void deleteUnidadMedida(Integer id);
}
