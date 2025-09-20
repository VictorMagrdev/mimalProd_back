package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.UnidadMedidaTipoInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaTipoResponse;

import java.util.List;

public interface UnidadMedidaTipoService {
    List<UnidadMedidaTipoResponse> getUnidadMedidaTipos();

    UnidadMedidaTipoResponse getUnidadMedidaTipoById(Long id);

    UnidadMedidaTipoResponse createUnidadMedidaTipo(UnidadMedidaTipoInput unidadMedidaTipoInput);

    UnidadMedidaTipoResponse updateUnidadMedidaTipo(Long id, UnidadMedidaTipoInput unidadMedidaTipoInput);

    void deleteUnidadMedidaTipo(Long id);
}
