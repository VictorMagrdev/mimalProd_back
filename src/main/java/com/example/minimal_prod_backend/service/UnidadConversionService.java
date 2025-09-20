package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.UnidadConversionInput;
import com.example.minimal_prod_backend.dto.UnidadConversionResponse;

import java.util.List;

public interface UnidadConversionService {
    List<UnidadConversionResponse> getUnidadConversiones();

    UnidadConversionResponse getUnidadConversionById(Long id);

    UnidadConversionResponse createUnidadConversion(UnidadConversionInput unidadConversionInput);

    UnidadConversionResponse updateUnidadConversion(Long id, UnidadConversionInput unidadConversionInput);

    void deleteUnidadConversion(Long id);
}
