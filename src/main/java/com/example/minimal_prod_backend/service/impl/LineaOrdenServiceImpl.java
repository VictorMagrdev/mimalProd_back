// LineaOrdenServiceImpl.java
package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.LineaOrden;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.events.LineaOrdenCreadaEvent;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.LineaOrdenRepository;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.service.LineaOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LineaOrdenServiceImpl implements LineaOrdenService {

    private final LineaOrdenRepository lineaOrdenRepository;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final ProductoRepository productoRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(readOnly = true)
    public List<LineaOrdenResponse> getLineasOrden() {
        return lineaOrdenRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LineaOrdenResponse getLineaOrdenById(Long id) {
        LineaOrden lineaOrden = lineaOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LineaOrden not found with id: " + id));
        return toResponse(lineaOrden);
    }

    @Override
    @Transactional
    public LineaOrdenResponse createLineaOrden(LineaOrdenInput lineaOrdenInput) {
        LineaOrden lineaOrden = toEntity(lineaOrdenInput);
        LineaOrden saved = lineaOrdenRepository.save(lineaOrden);

        // 🔥 Disparamos el evento al crear
        eventPublisher.publishEvent(new LineaOrdenCreadaEvent(this, saved.getId()));

        return toResponse(saved);
    }

    @Override
    @Transactional
    public LineaOrdenResponse updateLineaOrden(Long id, LineaOrdenInput lineaOrdenInput) {
        LineaOrden existingLineaOrden = lineaOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LineaOrden not found with id: " + id));
        updateEntityFromInput(lineaOrdenInput, existingLineaOrden);
        return toResponse(lineaOrdenRepository.save(existingLineaOrden));
    }

    @Override
    @Transactional
    public void deleteLineaOrden(Long id) {
        lineaOrdenRepository.deleteById(id);
    }

    // ------------------- MAPPERS -------------------
    private LineaOrdenResponse toResponse(LineaOrden entity) {
        if (entity == null) return null;
        LineaOrdenResponse dto = new LineaOrdenResponse();
        dto.setId(entity.getId());
        dto.setNumeroLinea(entity.getNumeroLinea());
        dto.setCantidadRequerida(entity.getCantidadRequerida());
        dto.setCantidadUsada(entity.getCantidadUsada());
        dto.setCostoUnitario(entity.getCostoUnitario());
        dto.setCostoTotal(entity.getCostoTotal());
        dto.setObservaciones(entity.getObservaciones());
        dto.setCreadoEn(entity.getCreadoEn());

        if (entity.getOrden() != null) {
            OrdenProduccionResponse ordenDto = new OrdenProduccionResponse();
            ordenDto.setId(entity.getOrden().getId());
            ordenDto.setNumeroOrden(entity.getOrden().getNumeroOrden());
            dto.setOrden(ordenDto);
        }

        if (entity.getProductoComponente() != null) {
            ProductoResponse productoDto = new ProductoResponse();
            productoDto.setId(entity.getProductoComponente().getId());
            productoDto.setCodigo(entity.getProductoComponente().getCodigo());
            productoDto.setNombre(entity.getProductoComponente().getNombre());
            dto.setProductoComponente(productoDto);
        }

        if (entity.getUnidadComponente() != null) {
            UnidadMedidaResponse unidadDto = new UnidadMedidaResponse();
            unidadDto.setId(entity.getUnidadComponente().getId());
            unidadDto.setNombre(entity.getUnidadComponente().getNombre());
            unidadDto.setAbreviatura(entity.getUnidadComponente().getAbreviatura());
            dto.setUnidadComponente(unidadDto);
        }

        return dto;
    }

    private LineaOrden toEntity(LineaOrdenInput dto) {
        if (dto == null) return null;
        LineaOrden entity = new LineaOrden();
        updateEntityFromInput(dto, entity);
        return entity;
    }

    private void updateEntityFromInput(LineaOrdenInput dto, LineaOrden entity) {
        if (dto == null || entity == null) return;

        entity.setNumeroLinea(dto.getNumeroLinea());
        entity.setCantidadRequerida(dto.getCantidadRequerida());
        entity.setCantidadUsada(dto.getCantidadUsada());
        entity.setCostoUnitario(dto.getCostoUnitario());
        entity.setObservaciones(dto.getObservaciones());

        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found"));
            entity.setOrden(orden);
        }
        if (dto.getIdProductoComponente() != null) {
            Producto producto = productoRepository.findById(dto.getIdProductoComponente())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found"));
            entity.setProductoComponente(producto);
        }
        if (dto.getIdUnidadComponente() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidadComponente())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found"));
            entity.setUnidadComponente(unidad);
        }
    }
}
