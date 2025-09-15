package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.TipoBodegaInput;
import com.example.minimal_prod_backend.dto.TipoBodegaResponse;
import com.example.minimal_prod_backend.entity.TipoBodega;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.TipoBodegaRepository;
import com.example.minimal_prod_backend.service.TipoBodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoBodegaServiceImpl implements TipoBodegaService {

    private final TipoBodegaRepository tipoBodegaRepository;

    @Override
    public List<TipoBodegaResponse> getTiposBodega() {
        return tipoBodegaRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TipoBodegaResponse getTipoBodegaById(Long id) {
        TipoBodega tipoBodega = tipoBodegaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoBodega not found with id: " + id));
        return toResponse(tipoBodega);
    }

    @Override
    public TipoBodegaResponse createTipoBodega(TipoBodegaInput tipoBodegaInput) {
        TipoBodega tipoBodega = toEntity(tipoBodegaInput);
        return toResponse(tipoBodegaRepository.save(tipoBodega));
    }

    @Override
    public TipoBodegaResponse updateTipoBodega(Long id, TipoBodegaInput tipoBodegaInput) {
        TipoBodega existingTipoBodega = tipoBodegaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoBodega not found with id: " + id));
        updateEntityFromInput(tipoBodegaInput, existingTipoBodega);
        return toResponse(tipoBodegaRepository.save(existingTipoBodega));
    }

    @Override
    public void deleteTipoBodega(Long id) {
        tipoBodegaRepository.deleteById(id);
    }

    private TipoBodegaResponse toResponse(TipoBodega entity) {
        if (entity == null) return null;
        TipoBodegaResponse dto = new TipoBodegaResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }

    private TipoBodega toEntity(TipoBodegaInput dto) {
        if (dto == null) return null;
        TipoBodega entity = new TipoBodega();
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }

    private void updateEntityFromInput(TipoBodegaInput dto, TipoBodega entity) {
        if (dto == null || entity == null) return;
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
    }
}
