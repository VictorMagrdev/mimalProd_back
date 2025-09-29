package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.LineaOrdenInput;
import com.example.minimal_prod_backend.dto.LineaOrdenResponse;

import java.util.List;

public interface LineaOrdenService {

    List<LineaOrdenResponse> getLineasOrden();

    LineaOrdenResponse getLineaOrdenById(Long id);

    LineaOrdenResponse createLineaOrden(LineaOrdenInput lineaOrdenInput);

    LineaOrdenResponse updateLineaOrden(Long id, LineaOrdenInput lineaOrdenInput);

    void deleteLineaOrden(Long id);
}
