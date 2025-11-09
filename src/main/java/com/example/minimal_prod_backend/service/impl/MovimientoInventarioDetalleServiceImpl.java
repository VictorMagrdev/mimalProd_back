package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.MovimientoInventarioDetalleRequest;
import com.example.minimal_prod_backend.dto.Response.MovimientoInventarioDetalleResponse;
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
    public MovimientoInventarioDetalleResponse createMovimientoInventarioDetalle(MovimientoInventarioDetalleRequest input) {
        MovimientoInventarioDetalle entity = mapper.toEntity(input);
        attachRelations(input, entity);
        return mapper.toResponse(movimientoInventarioDetalleRepository.save(entity));
    }

    @Override
    public MovimientoInventarioDetalleResponse updateMovimientoInventarioDetalle(Long id, MovimientoInventarioDetalleRequest input) {
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

    private void attachRelations(MovimientoInventarioDetalleRequest dto, MovimientoInventarioDetalle entity) {
        if (dto.movimientoId() != null) {
            MovimientoInventario movimiento = movimientoInventarioRepository.findById(dto.movimientoId())
                    .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario not found with id: " + dto.movimientoId()));
            entity.setMovimiento(movimiento);
        }

        if (dto.productoId() != null) {
            Producto producto = productoRepository.findById(dto.productoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.productoId()));
            entity.setProducto(producto);
        }

        if (dto.loteId() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.loteId())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.loteId()));
            entity.setLote(lote);
        }

        if (dto.unidadId() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.unidadId())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.unidadId()));
            entity.setUnidad(unidad);
        }
    }
}
