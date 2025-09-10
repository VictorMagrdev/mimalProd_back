package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.UnidadMedidaTipoInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaTipoResponse;

import java.util.List;

public interface UnidadMedidaTipoService {
    List<UnidadMedidaTipoResponse> getUnidadMedidaTipos();
    UnidadMedidaTipoResponse getUnidadMedidaTipoById(Integer id);
    UnidadMedidaTipoResponse createUnidadMedidaTipo(UnidadMedidaTipoInput unidadMedidaTipoInput);
    UnidadMedidaTipoResponse updateUnidadMedidaTipo(Integer id, UnidadMedidaTipoInput unidadMedidaTipoInput);
    void deleteUnidadMedidaTipo(Integer id);
}
