package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.MovimientoInventarioDetalleInput;
import com.example.minimal_prod_backend.dto.MovimientoInventarioDetalleResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.MovimientoInventarioDetalleMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.MovimientoInventarioDetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioDetalleServiceImpl implements MovimientoInventarioDetalleService {

    private final MovimientoInventarioDetalleRepository movimientoInventarioDetalleRepository;
    private final MovimientoInventarioRepository movimientoInventarioRepository;
    private final ProductoRepository productoRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final MovimientoInventarioDetalleMapper mapper;

    @Override
    public List<MovimientoInventarioDetalleResponse> getMovimientosInventarioDetalle() {
        return movimientoInventarioDetalleRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public MovimientoInventarioDetalleResponse getMovimientoInventarioDetalleById(Long id) {
        MovimientoInventarioDetalle entity = movimientoInventarioDetalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventarioDetalle not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    public MovimientoInventarioDetalleResponse createMovimientoInventarioDetalle(MovimientoInventarioDetalleInput input) {
        MovimientoInventarioDetalle entity = mapper.toEntity(input);
        attachRelations(input, entity);
        return mapper.toResponse(movimientoInventarioDetalleRepository.save(entity));
    }

    @Override
    public MovimientoInventarioDetalleResponse updateMovimientoInventarioDetalle(Long id, MovimientoInventarioDetalleInput input) {
        MovimientoInventarioDetalle entity = movimientoInventarioDetalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventarioDetalle not found with id: " + id));
        mapper.updateEntityFromInput(input, entity);
        attachRelations(input, entity);
        return mapper.toResponse(movimientoInventarioDetalleRepository.save(entity));
    }

    @Override
    public void deleteMovimientoInventarioDetalle(Long id) {
        movimientoInventarioDetalleRepository.deleteById(id);
    }

    private void attachRelations(MovimientoInventarioDetalleInput dto, MovimientoInventarioDetalle entity) {
        if (dto.getMovimientoId() != null) {
            MovimientoInventario movimiento = movimientoInventarioRepository.findById(dto.getMovimientoId())
                    .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario not found with id: " + dto.getMovimientoId()));
            entity.setMovimiento(movimiento);
        }

        if (dto.getProductoId() != null) {
            Producto producto = productoRepository.findById(dto.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getProductoId()));
            entity.setProducto(producto);
        }

        if (dto.getLoteId() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getLoteId())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.getLoteId()));
            entity.setLote(lote);
        }

        if (dto.getUnidadId() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getUnidadId())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getUnidadId()));
            entity.setUnidad(unidad);
        }
    }
}
