package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.LineaOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.LineaOrdenResponse;

import java.util.List;

public interface LineaOrdenService {

    List<LineaOrdenResponse> getLineasOrden();

    LineaOrdenResponse getLineaOrdenById(Long id);

    LineaOrdenResponse createLineaOrden(LineaOrdenRequest lineaOrdenInput);

    LineaOrdenResponse updateLineaOrden(Long id, LineaOrdenRequest lineaOrdenInput);

    void deleteLineaOrden(Long id);
}
