package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.BodegaResponse;
import com.example.minimal_prod_backend.dto.MovimientoInventarioInput;
import com.example.minimal_prod_backend.dto.MovimientoInventarioResponse;
import com.example.minimal_prod_backend.dto.TipoMovimientoResponse;
import com.example.minimal_prod_backend.entity.Bodega;
import com.example.minimal_prod_backend.entity.MovimientoInventario;
import com.example.minimal_prod_backend.entity.TipoMovimiento;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.BodegaRepository;
import com.example.minimal_prod_backend.repository.MovimientoInventarioRepository;
import com.example.minimal_prod_backend.repository.TipoMovimientoRepository;
import com.example.minimal_prod_backend.service.MovimientoInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoInventarioRepository;
    private final BodegaRepository bodegaRepository;
    private final TipoMovimientoRepository tipoMovimientoRepository;

    @Override
    public List<MovimientoInventarioResponse> getMovimientosInventario() {
        return movimientoInventarioRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoInventarioResponse getMovimientoInventarioById(Long id) {
        MovimientoInventario movimientoInventario = movimientoInventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario not found with id: " + id));
        return toResponse(movimientoInventario);
    }

    @Override
    public MovimientoInventarioResponse createMovimientoInventario(MovimientoInventarioInput movimientoInventarioInput) {
        MovimientoInventario movimientoInventario = toEntity(movimientoInventarioInput);
        return toResponse(movimientoInventarioRepository.save(movimientoInventario));
    }

    @Override
    public MovimientoInventarioResponse updateMovimientoInventario(Long id, MovimientoInventarioInput movimientoInventarioInput) {
        MovimientoInventario existingMovimientoInventario = movimientoInventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario not found with id: " + id));
        updateEntityFromInput(movimientoInventarioInput, existingMovimientoInventario);
        return toResponse(movimientoInventarioRepository.save(existingMovimientoInventario));
    }

    @Override
    public void deleteMovimientoInventario(Long id) {
        movimientoInventarioRepository.deleteById(id);
    }

    private MovimientoInventarioResponse toResponse(MovimientoInventario entity) {
        if (entity == null) return null;
        MovimientoInventarioResponse dto = new MovimientoInventarioResponse();
        dto.setId(entity.getId());
        dto.setFecha(entity.getFecha());
        dto.setObservaciones(entity.getObservaciones());
        dto.setCreadoPor(entity.getCreadoPor());
        dto.setCreadoEn(entity.getCreado_en());

        if (entity.getBodegaOrigen() != null) {
            dto.setBodegaOrigen(new BodegaResponse());
            dto.getBodegaOrigen().setId(entity.getBodegaOrigen().getId());
            dto.getBodegaOrigen().setNombre(entity.getBodegaOrigen().getNombre());
        }

        if (entity.getBodegaDestino() != null) {
            dto.setBodegaDestino(new BodegaResponse());
            dto.getBodegaDestino().setId(entity.getBodegaDestino().getId());
            dto.getBodegaDestino().setNombre(entity.getBodegaDestino().getNombre());
        }

        if (entity.getTipoMovimiento() != null) {
            dto.setTipoMovimiento(new TipoMovimientoResponse());
            dto.getTipoMovimiento().setId(entity.getTipoMovimiento().getId());
            dto.getTipoMovimiento().setNombre(entity.getTipoMovimiento().getNombre());
        }

        return dto;
    }

    private MovimientoInventario toEntity(MovimientoInventarioInput dto) {
        if (dto == null) return null;
        MovimientoInventario entity = new MovimientoInventario();
        entity.setObservaciones(dto.getObservaciones());
        entity.setCreadoPor(dto.getCreadoPor());

        if (dto.getIdBodegaOrigen() != null) {
            Bodega bodegaOrigen = bodegaRepository.findById(dto.getIdBodegaOrigen())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega Origen not found with id: " + dto.getIdBodegaOrigen()));
            entity.setBodegaOrigen(bodegaOrigen);
        }

        if (dto.getIdBodegaDestino() != null) {
            Bodega bodegaDestino = bodegaRepository.findById(dto.getIdBodegaDestino())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega Destino not found with id: " + dto.getIdBodegaDestino()));
            entity.setBodegaDestino(bodegaDestino);
        }

        if (dto.getIdTipoMovimiento() != null) {
            TipoMovimiento tipoMovimiento = tipoMovimientoRepository.findById(dto.getIdTipoMovimiento())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo Movimiento not found with id: " + dto.getIdTipoMovimiento()));
            entity.setTipoMovimiento(tipoMovimiento);
        }

        return entity;
    }

    private void updateEntityFromInput(MovimientoInventarioInput dto, MovimientoInventario entity) {
        if (dto == null || entity == null) return;

        entity.setObservaciones(dto.getObservaciones());
        entity.setCreadoPor(dto.getCreadoPor());

        if (dto.getIdBodegaOrigen() != null) {
            Bodega bodegaOrigen = bodegaRepository.findById(dto.getIdBodegaOrigen())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega Origen not found with id: " + dto.getIdBodegaOrigen()));
            entity.setBodegaOrigen(bodegaOrigen);
        }

        if (dto.getIdBodegaDestino() != null) {
            Bodega bodegaDestino = bodegaRepository.findById(dto.getIdBodegaDestino())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega Destino not found with id: " + dto.getIdBodegaDestino()));
            entity.setBodegaDestino(bodegaDestino);
        }

        if (dto.getIdTipoMovimiento() != null) {
            TipoMovimiento tipoMovimiento = tipoMovimientoRepository.findById(dto.getIdTipoMovimiento())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo Movimiento not found with id: " + dto.getIdTipoMovimiento()));
            entity.setTipoMovimiento(tipoMovimiento);
        }
    }
}
