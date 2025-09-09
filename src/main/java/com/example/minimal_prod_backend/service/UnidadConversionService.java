package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.UnidadConversion;

import java.util.List;

public interface UnidadConversionService {
    List<UnidadConversion> getUnidadConversiones();
    UnidadConversion getUnidadConversionById(Long id);
    UnidadConversion createUnidadConversion(UnidadConversion unidadConversion);
    UnidadConversion updateUnidadConversion(Long id, UnidadConversion unidadConversion);
    void deleteUnidadConversion(Long id);
}
