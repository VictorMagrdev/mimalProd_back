package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.TipoMovimientoInput;
import com.example.minimal_prod_backend.dto.TipoMovimientoResponse;
import com.example.minimal_prod_backend.entity.TipoMovimiento;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.TipoMovimientoRepository;
import com.example.minimal_prod_backend.service.TipoMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoMovimientoServiceImpl implements TipoMovimientoService {

    private final TipoMovimientoRepository tipoMovimientoRepository;

    @Override
    public List<TipoMovimientoResponse> getTiposMovimiento() {
        return tipoMovimientoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TipoMovimientoResponse getTipoMovimientoById(Long id) {
        TipoMovimiento tipoMovimiento = tipoMovimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoMovimiento not found with id: " + id));
        return toResponse(tipoMovimiento);
    }

    @Override
    public TipoMovimientoResponse createTipoMovimiento(TipoMovimientoInput tipoMovimientoInput) {
        TipoMovimiento tipoMovimiento = toEntity(tipoMovimientoInput);
        return toResponse(tipoMovimientoRepository.save(tipoMovimiento));
    }

    @Override
    public TipoMovimientoResponse updateTipoMovimiento(Long id, TipoMovimientoInput tipoMovimientoInput) {
        TipoMovimiento existingTipoMovimiento = tipoMovimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoMovimiento not found with id: " + id));
        updateEntityFromInput(tipoMovimientoInput, existingTipoMovimiento);
        return toResponse(tipoMovimientoRepository.save(existingTipoMovimiento));
    }

    @Override
    public void deleteTipoMovimiento(Long id) {
        tipoMovimientoRepository.deleteById(id);
    }

    private TipoMovimientoResponse toResponse(TipoMovimiento entity) {
        if (entity == null) return null;
        TipoMovimientoResponse dto = new TipoMovimientoResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setAfectaWip(entity.getAfecta_wip());
        dto.setCreadoEn(entity.getCreadoEn());
        return dto;
    }

    private TipoMovimiento toEntity(TipoMovimientoInput dto) {
        if (dto == null) return null;
        TipoMovimiento entity = new TipoMovimiento();
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setAfecta_wip(dto.getAfectaWip());
        return entity;
    }

    private void updateEntityFromInput(TipoMovimientoInput dto, TipoMovimiento entity) {
        if (dto == null || entity == null) return;
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setAfecta_wip(dto.getAfectaWip());
    }
}
