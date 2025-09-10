package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.LineaOrdenInput;
import com.example.minimal_prod_backend.dto.LineaOrdenResponse;

import java.util.List;

public interface LineaOrdenService {
    List<LineaOrdenResponse> getLineasOrden();
    LineaOrdenResponse getLineaOrdenById(Integer id);
    LineaOrdenResponse createLineaOrden(LineaOrdenInput lineaOrdenInput);
    LineaOrdenResponse updateLineaOrden(Integer id, LineaOrdenInput lineaOrdenInput);
    void deleteLineaOrden(Integer id);
}
