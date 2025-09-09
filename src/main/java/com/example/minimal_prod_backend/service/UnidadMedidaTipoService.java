package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;

import java.util.List;

public interface UnidadMedidaTipoService {
    List<UnidadMedidaTipo> getUnidadMedidaTipos();
    UnidadMedidaTipo getUnidadMedidaTipoById(Long id);
    UnidadMedidaTipo createUnidadMedidaTipo(UnidadMedidaTipo unidadMedidaTipo);
    UnidadMedidaTipo updateUnidadMedidaTipo(Long id, UnidadMedidaTipo unidadMedidaTipo);
    void deleteUnidadMedidaTipo(Long id);
}
