package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.MovimientoInventarioRequest;
import com.example.minimal_prod_backend.dto.Response.MovimientoInventarioResponse;
import com.example.minimal_prod_backend.entity.Bodega;
import com.example.minimal_prod_backend.entity.MovimientoInventario;
import com.example.minimal_prod_backend.entity.TipoMovimiento;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.MovimientoInventarioMapper;
import com.example.minimal_prod_backend.repository.BodegaRepository;
import com.example.minimal_prod_backend.repository.MovimientoInventarioRepository;
import com.example.minimal_prod_backend.repository.TipoMovimientoRepository;
import com.example.minimal_prod_backend.service.MovimientoInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoInventarioRepository;
    private final BodegaRepository bodegaRepository;
    private final TipoMovimientoRepository tipoMovimientoRepository;
    private final MovimientoInventarioMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoInventarioResponse> getMovimientosInventario() {
        return mapper.toResponseList(movimientoInventarioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public MovimientoInventarioResponse getMovimientoInventarioById(Long id) {
        MovimientoInventario movimientoInventario = movimientoInventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario not found with id: " + id));
        return mapper.toResponse(movimientoInventario);
    }

    @Override
    @Transactional
    public MovimientoInventarioResponse createMovimientoInventario(MovimientoInventarioRequest input) {
        MovimientoInventario entity = mapper.toEntity(input);
        attachRelations(input, entity);
        MovimientoInventario saved = movimientoInventarioRepository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public MovimientoInventarioResponse updateMovimientoInventario(Long id, MovimientoInventarioRequest input) {
        MovimientoInventario existing = movimientoInventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario not found with id: " + id));

        mapper.updateEntityFromInput(input, existing);
        attachRelations(input, existing);

        return mapper.toResponse(movimientoInventarioRepository.save(existing));
    }

    @Transactional
    public void deleteMovimientoInventario(Long id) {
        movimientoInventarioRepository.deleteById(id);
    }

    /**
     * Maneja relaciones con otras entidades (BodegaOrigen, BodegaDestino, TipoMovimiento).
     */
    private void attachRelations(MovimientoInventarioRequest dto, MovimientoInventario entity) {
        if (dto.bodegaOrigenId() != null) {
            Bodega bodegaOrigen = bodegaRepository.findById(dto.bodegaOrigenId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega Origen not found with id: " + dto.bodegaOrigenId()));
            entity.setBodegaOrigen(bodegaOrigen);
        }

        if (dto.bodegaDestinoId() != null) {
            Bodega bodegaDestino = bodegaRepository.findById(dto.bodegaDestinoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega Destino not found with id: " + dto.bodegaDestinoId()));
            entity.setBodegaDestino(bodegaDestino);
        }

        if (dto.tipoMovimientoId() != null) {
            TipoMovimiento tipoMovimiento = tipoMovimientoRepository.findById(dto.tipoMovimientoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo Movimiento not found with id: " + dto.tipoMovimientoId()));
            entity.setTipoMovimiento(tipoMovimiento);
        }
    }
}
