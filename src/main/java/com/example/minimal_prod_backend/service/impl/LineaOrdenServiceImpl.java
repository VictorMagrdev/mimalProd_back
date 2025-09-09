package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.LineaOrden;
import com.example.minimal_prod_backend.repository.LineaOrdenRepository;
import com.example.minimal_prod_backend.service.LineaOrdenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaOrdenServiceImpl implements LineaOrdenService {

    private final LineaOrdenRepository lineaOrdenRepository;

    public LineaOrdenServiceImpl(LineaOrdenRepository lineaOrdenRepository) {
        this.lineaOrdenRepository = lineaOrdenRepository;
    }

    @Override
    public List<LineaOrden> getLineasOrden() {
        return lineaOrdenRepository.findAll();
    }

    @Override
    public LineaOrden getLineaOrdenById(Long id) {
        return lineaOrdenRepository.findById(id).orElse(null);
    }

    @Override
    public LineaOrden createLineaOrden(LineaOrden lineaOrden) {
        return lineaOrdenRepository.save(lineaOrden);
    }

    @Override
    public LineaOrden updateLineaOrden(Long id, LineaOrden lineaOrden) {
        return lineaOrdenRepository.findById(id).map(existingLineaOrden -> {
            existingLineaOrden.setOrden(lineaOrden.getOrden());
            existingLineaOrden.setNumeroLinea(lineaOrden.getNumeroLinea());
            existingLineaOrden.setProductoComponente(lineaOrden.getProductoComponente());
            existingLineaOrden.setCantidadRequerida(lineaOrden.getCantidadRequerida());
            existingLineaOrden.setUnidadComponente(lineaOrden.getUnidadComponente());
            existingLineaOrden.setCantidadUsada(lineaOrden.getCantidadUsada());
            existingLineaOrden.setCostoUnitario(lineaOrden.getCostoUnitario());
            existingLineaOrden.setObservaciones(lineaOrden.getObservaciones());
            return lineaOrdenRepository.save(existingLineaOrden);
        }).orElse(null);
    }

    @Override
    public void deleteLineaOrden(Long id) {
        lineaOrdenRepository.deleteById(id);
    }
}
