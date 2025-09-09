package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.UnidadMedida;

import java.util.List;

public interface UnidadMedidaService {
    List<UnidadMedida> getUnidadesMedida();
    UnidadMedida getUnidadMedidaById(Long id);
    UnidadMedida createUnidadMedida(UnidadMedida unidadMedida);
    UnidadMedida updateUnidadMedida(Long id, UnidadMedida unidadMedida);
    void deleteUnidadMedida(Long id);
}
