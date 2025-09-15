package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.ProductoResponse;
import com.example.minimal_prod_backend.dto.PuntoReordenInput;
import com.example.minimal_prod_backend.dto.PuntoReordenResponse;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.entity.PuntoReorden;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.repository.PuntoReordenRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.service.PuntoReordenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PuntoReordenServiceImpl implements PuntoReordenService {

    private final PuntoReordenRepository puntoReordenRepository;
    private final ProductoRepository productoRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<PuntoReordenResponse> getPuntosReorden() {
        return puntoReordenRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PuntoReordenResponse getPuntoReordenById(Long id) {
        PuntoReorden puntoReorden = puntoReordenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PuntoReorden not found with id: " + id));
        return toResponse(puntoReorden);
    }

    @Override
    public PuntoReordenResponse createPuntoReorden(PuntoReordenInput puntoReordenInput) {
        PuntoReorden puntoReorden = toEntity(puntoReordenInput);
        return toResponse(puntoReordenRepository.save(puntoReorden));
    }

    @Override
    public PuntoReordenResponse updatePuntoReorden(Long id, PuntoReordenInput puntoReordenInput) {
        PuntoReorden existingPuntoReorden = puntoReordenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PuntoReorden not found with id: " + id));
        updateEntityFromInput(puntoReordenInput, existingPuntoReorden);
        return toResponse(puntoReordenRepository.save(existingPuntoReorden));
    }

    @Override
    public void deletePuntoReorden(Long id) {
        puntoReordenRepository.deleteById(id);
    }

    private PuntoReordenResponse toResponse(PuntoReorden entity) {
        if (entity == null) return null;
        PuntoReordenResponse dto = new PuntoReordenResponse();
        dto.setId(entity.getId());
        dto.setStockMinimo(entity.getStockMinimo());
        dto.setStockSeguridad(entity.getStockSeguridad());

        if (entity.getProducto() != null) {
            dto.setProducto(new ProductoResponse());
            dto.getProducto().setId(entity.getProducto().getId());
            dto.getProducto().setNombre(entity.getProducto().getNombre());
        }

        if (entity.getUnidad() != null) {
            dto.setUnidad(new UnidadMedidaResponse());
            dto.getUnidad().setId(entity.getUnidad().getId());
            dto.getUnidad().setNombre(entity.getUnidad().getNombre());
        }

        return dto;
    }

    private PuntoReorden toEntity(PuntoReordenInput dto) {
        if (dto == null) return null;
        PuntoReorden entity = new PuntoReorden();
        entity.setStockMinimo(dto.getStockMinimo());
        entity.setStockSeguridad(dto.getStockSeguridad());

        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        }

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }

        return entity;
    }

    private void updateEntityFromInput(PuntoReordenInput dto, PuntoReorden entity) {
        if (dto == null || entity == null) return;

        entity.setStockMinimo(dto.getStockMinimo());
        entity.setStockSeguridad(dto.getStockSeguridad());

        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        }

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }
    }
}
