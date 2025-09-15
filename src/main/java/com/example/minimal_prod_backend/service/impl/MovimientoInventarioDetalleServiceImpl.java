package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.MovimientoInventarioDetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioDetalleServiceImpl implements MovimientoInventarioDetalleService {

    private final MovimientoInventarioDetalleRepository movimientoInventarioDetalleRepository;
    private final MovimientoInventarioRepository movimientoInventarioRepository;
    private final ProductoRepository productoRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<MovimientoInventarioDetalleResponse> getMovimientosInventarioDetalle() {
        return movimientoInventarioDetalleRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoInventarioDetalleResponse getMovimientoInventarioDetalleById(Long id) {
        MovimientoInventarioDetalle movimientoInventarioDetalle = movimientoInventarioDetalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventarioDetalle not found with id: " + id));
        return toResponse(movimientoInventarioDetalle);
    }

    @Override
    public MovimientoInventarioDetalleResponse createMovimientoInventarioDetalle(MovimientoInventarioDetalleInput movimientoInventarioDetalleInput) {
        MovimientoInventarioDetalle movimientoInventarioDetalle = toEntity(movimientoInventarioDetalleInput);
        return toResponse(movimientoInventarioDetalleRepository.save(movimientoInventarioDetalle));
    }

    @Override
    public MovimientoInventarioDetalleResponse updateMovimientoInventarioDetalle(Long id, MovimientoInventarioDetalleInput movimientoInventarioDetalleInput) {
        MovimientoInventarioDetalle existingMovimientoInventarioDetalle = movimientoInventarioDetalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventarioDetalle not found with id: " + id));
        updateEntityFromInput(movimientoInventarioDetalleInput, existingMovimientoInventarioDetalle);
        return toResponse(movimientoInventarioDetalleRepository.save(existingMovimientoInventarioDetalle));
    }

    @Override
    public void deleteMovimientoInventarioDetalle(Long id) {
        movimientoInventarioDetalleRepository.deleteById(id);
    }

    private MovimientoInventarioDetalleResponse toResponse(MovimientoInventarioDetalle entity) {
        if (entity == null) return null;
        MovimientoInventarioDetalleResponse dto = new MovimientoInventarioDetalleResponse();
        dto.setId(entity.getId());
        dto.setCantidad(entity.getCantidad());
        dto.setCostoUnitario(entity.getCostoUnitario());
        dto.setCostoTotal(entity.getCostoTotal());

        if (entity.getMovimiento() != null) {
            dto.setIdMovimiento(entity.getMovimiento().getId());
        }

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

        if (entity.getUnidad() != null) {
            dto.setUnidad(new UnidadMedidaResponse());
            dto.getUnidad().setId(entity.getUnidad().getId());
            dto.getUnidad().setNombre(entity.getUnidad().getNombre());
        }

        return dto;
    }

    private MovimientoInventarioDetalle toEntity(MovimientoInventarioDetalleInput dto) {
        if (dto == null) return null;
        MovimientoInventarioDetalle entity = new MovimientoInventarioDetalle();
        entity.setCantidad(dto.getCantidad());
        entity.setCostoUnitario(dto.getCostoUnitario());

        if (dto.getIdMovimiento() != null) {
            MovimientoInventario movimiento = movimientoInventarioRepository.findById(dto.getIdMovimiento())
                    .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario not found with id: " + dto.getIdMovimiento()));
            entity.setMovimiento(movimiento);
        }

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

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }

        return entity;
    }

    private void updateEntityFromInput(MovimientoInventarioDetalleInput dto, MovimientoInventarioDetalle entity) {
        if (dto == null || entity == null) return;

        entity.setCantidad(dto.getCantidad());
        entity.setCostoUnitario(dto.getCostoUnitario());

        if (dto.getIdMovimiento() != null) {
            MovimientoInventario movimiento = movimientoInventarioRepository.findById(dto.getIdMovimiento())
                    .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario not found with id: " + dto.getIdMovimiento()));
            entity.setMovimiento(movimiento);
        }

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

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }
    }
}
