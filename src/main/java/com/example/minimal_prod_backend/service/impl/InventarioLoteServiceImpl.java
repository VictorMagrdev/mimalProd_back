package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.InventarioLoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioLoteServiceImpl implements InventarioLoteService {

    private final InventarioLoteRepository inventarioLoteRepository;
    private final ProductoRepository productoRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final BodegaRepository bodegaRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<InventarioLoteResponse> getInventarioLotes() {
        return inventarioLoteRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InventarioLoteResponse getInventarioLoteById(Long id) {
        InventarioLote inventarioLote = inventarioLoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InventarioLote not found with id: " + id));
        return toResponse(inventarioLote);
    }

    @Override
    public InventarioLoteResponse createInventarioLote(InventarioLoteInput inventarioLoteInput) {
        InventarioLote inventarioLote = toEntity(inventarioLoteInput);
        inventarioLote.setActualizadoEn(LocalDateTime.now());
        return toResponse(inventarioLoteRepository.save(inventarioLote));
    }

    @Override
    public InventarioLoteResponse updateInventarioLote(Long id, InventarioLoteInput inventarioLoteInput) {
        InventarioLote existingInventarioLote = inventarioLoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InventarioLote not found with id: " + id));
        updateEntityFromInput(inventarioLoteInput, existingInventarioLote);
        existingInventarioLote.setActualizadoEn(LocalDateTime.now());
        return toResponse(inventarioLoteRepository.save(existingInventarioLote));
    }

    @Override
    public void deleteInventarioLote(Long id) {
        inventarioLoteRepository.deleteById(id);
    }

    private InventarioLoteResponse toResponse(InventarioLote entity) {
        if (entity == null) return null;
        InventarioLoteResponse dto = new InventarioLoteResponse();
        dto.setId(entity.getId());
        dto.setCantidad(entity.getCantidad());
        dto.setActualizadoEn(entity.getActualizadoEn());

        if (entity.getProducto() != null) {
            dto.setProducto(new ProductoResponse());
            dto.getProducto().setId(entity.getProducto().getId());
            dto.getProducto().setNombre(entity.getProducto().getNombre());
        }

        if (entity.getLote() != null) {
            dto.setLote(new LoteProduccionResponse());
            dto.getLote().setId(entity.getLote().getId());
            dto.getLote().setNumeroLote(entity.getLote().getNumeroLote());
        }

        if (entity.getBodega() != null) {
            dto.setBodega(new BodegaResponse());
            dto.getBodega().setId(entity.getBodega().getId());
            dto.getBodega().setNombre(entity.getBodega().getNombre());
        }

        if (entity.getUnidad() != null) {
            dto.setUnidad(new UnidadMedidaResponse());
            dto.getUnidad().setId(entity.getUnidad().getId());
            dto.getUnidad().setNombre(entity.getUnidad().getNombre());
        }

        return dto;
    }

    private InventarioLote toEntity(InventarioLoteInput dto) {
        if (dto == null) return null;
        InventarioLote entity = new InventarioLote();
        entity.setCantidad(dto.getCantidad());

        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        }

        if (dto.getIdLote() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getIdLote())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.getIdLote()));
            entity.setLote(lote);
        }

        if (dto.getIdBodega() != null) {
            Bodega bodega = bodegaRepository.findById(dto.getIdBodega())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + dto.getIdBodega()));
            entity.setBodega(bodega);
        }

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }

        return entity;
    }

    private void updateEntityFromInput(InventarioLoteInput dto, InventarioLote entity) {
        if (dto == null || entity == null) return;

        entity.setCantidad(dto.getCantidad());

        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        }

        if (dto.getIdLote() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getIdLote())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.getIdLote()));
            entity.setLote(lote);
        }

        if (dto.getIdBodega() != null) {
            Bodega bodega = bodegaRepository.findById(dto.getIdBodega())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + dto.getIdBodega()));
            entity.setBodega(bodega);
        }

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }
    }
}
