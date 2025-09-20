// MovimientoInventarioServiceImpl.java
package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.BodegaResponse;
import com.example.minimal_prod_backend.dto.MovimientoInventarioInput;
import com.example.minimal_prod_backend.dto.MovimientoInventarioResponse;
import com.example.minimal_prod_backend.dto.TipoMovimientoResponse;
import com.example.minimal_prod_backend.entity.Bodega;
import com.example.minimal_prod_backend.entity.MovimientoInventario;
import com.example.minimal_prod_backend.entity.TipoMovimiento;
import com.example.minimal_prod_backend.events.MovimientoAjusteEvent;
import com.example.minimal_prod_backend.events.MovimientoRegistradoEvent;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.BodegaRepository;
import com.example.minimal_prod_backend.repository.MovimientoInventarioRepository;
import com.example.minimal_prod_backend.repository.TipoMovimientoRepository;
import com.example.minimal_prod_backend.service.MovimientoInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoInventarioRepository;
    private final BodegaRepository bodegaRepository;
    private final TipoMovimientoRepository tipoMovimientoRepository;
    private final ApplicationEventPublisher eventPublisher;

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
    @Transactional
    public MovimientoInventarioResponse createMovimientoInventario(MovimientoInventarioInput movimientoInventarioInput) {
        MovimientoInventario movimientoInventario = toEntity(movimientoInventarioInput);
        MovimientoInventario saved = movimientoInventarioRepository.save(movimientoInventario);

        // 🔥 Publicar evento siempre que se cree
        eventPublisher.publishEvent(new MovimientoRegistradoEvent(this, saved.getId()));

        // 🔥 Si es un ajuste, lanzar evento especial
        if (saved.getTipoMovimiento() != null &&
                "AJUSTE".equalsIgnoreCase(saved.getTipoMovimiento().getNombre())) {
            eventPublisher.publishEvent(new MovimientoAjusteEvent(this, saved.getId()));
        }

        return toResponse(saved);
    }

    @Override
    @Transactional
    public MovimientoInventarioResponse updateMovimientoInventario(Long id, MovimientoInventarioInput movimientoInventarioInput) {
        MovimientoInventario existing = movimientoInventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario not found with id: " + id));
        updateEntityFromInput(movimientoInventarioInput, existing);
        return toResponse(movimientoInventarioRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteMovimientoInventario(Long id) {
        movimientoInventarioRepository.deleteById(id);
    }

    // ------------------- MAPPERS -------------------
    private MovimientoInventarioResponse toResponse(MovimientoInventario entity) {
        if (entity == null) return null;
        MovimientoInventarioResponse dto = new MovimientoInventarioResponse();
        dto.setId(entity.getId());
        dto.setFecha(entity.getFecha());
        dto.setObservaciones(entity.getObservaciones());
        dto.setCreadoPor(entity.getCreadoPor());
        dto.setCreadoEn(entity.getCreado_en());

        if (entity.getBodegaOrigen() != null) {
            BodegaResponse origenDto = new BodegaResponse();
            origenDto.setId(entity.getBodegaOrigen().getId());
            origenDto.setNombre(entity.getBodegaOrigen().getNombre());
            dto.setBodegaOrigen(origenDto);
        }

        if (entity.getBodegaDestino() != null) {
            BodegaResponse destinoDto = new BodegaResponse();
            destinoDto.setId(entity.getBodegaDestino().getId());
            destinoDto.setNombre(entity.getBodegaDestino().getNombre());
            dto.setBodegaDestino(destinoDto);
        }

        if (entity.getTipoMovimiento() != null) {
            TipoMovimientoResponse tipoDto = new TipoMovimientoResponse();
            tipoDto.setId(entity.getTipoMovimiento().getId());
            tipoDto.setNombre(entity.getTipoMovimiento().getNombre());
            dto.setTipoMovimiento(tipoDto);
        }

        return dto;
    }

    private MovimientoInventario toEntity(MovimientoInventarioInput dto) {
        if (dto == null) return null;
        MovimientoInventario entity = new MovimientoInventario();
        updateEntityFromInput(dto, entity);
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
