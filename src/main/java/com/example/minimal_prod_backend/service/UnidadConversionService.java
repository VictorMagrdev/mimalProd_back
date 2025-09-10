package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.UnidadConversionInput;
import com.example.minimal_prod_backend.dto.UnidadConversionResponse;

import java.util.List;

public interface UnidadConversionService {
    List<UnidadConversionResponse> getUnidadConversiones();
    UnidadConversionResponse getUnidadConversionById(Integer id);
    UnidadConversionResponse createUnidadConversion(UnidadConversionInput unidadConversionInput);
    UnidadConversionResponse updateUnidadConversion(Integer id, UnidadConversionInput unidadConversionInput);
    void deleteUnidadConversion(Integer id);
}
