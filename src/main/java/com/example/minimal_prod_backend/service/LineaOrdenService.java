package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.LineaOrden;

import java.util.List;

public interface LineaOrdenService {
    List<LineaOrden> getLineasOrden();
    LineaOrden getLineaOrdenById(Long id);
    LineaOrden createLineaOrden(LineaOrden lineaOrden);
    LineaOrden updateLineaOrden(Long id, LineaOrden lineaOrden);
    void deleteLineaOrden(Long id);
}
