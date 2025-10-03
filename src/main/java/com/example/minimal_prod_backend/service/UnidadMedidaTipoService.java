package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.UnidadMedidaTipoRequest;
import com.example.minimal_prod_backend.dto.UnidadMedidaTipoResponse;

import java.util.List;

public interface UnidadMedidaTipoService {
    List<UnidadMedidaTipoResponse> getUnidadMedidaTipos();

    UnidadMedidaTipoResponse getUnidadMedidaTipoById(Long id);

    UnidadMedidaTipoResponse createUnidadMedidaTipo(UnidadMedidaTipoRequest unidadMedidaTipoInput);

    UnidadMedidaTipoResponse updateUnidadMedidaTipo(Long id, UnidadMedidaTipoRequest unidadMedidaTipoInput);

    void deleteUnidadMedidaTipo(Long id);
}
