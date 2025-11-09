package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.UnidadConversionRequest;
import com.example.minimal_prod_backend.dto.Response.UnidadConversionResponse;

import java.util.List;

public interface UnidadConversionService {
    List<UnidadConversionResponse> getUnidadConversiones();

    UnidadConversionResponse getUnidadConversionById(Long id);

    UnidadConversionResponse createUnidadConversion(UnidadConversionRequest unidadConversionInput);

    UnidadConversionResponse updateUnidadConversion(Long id, UnidadConversionRequest unidadConversionInput);

    void deleteUnidadConversion(Long id);
}
