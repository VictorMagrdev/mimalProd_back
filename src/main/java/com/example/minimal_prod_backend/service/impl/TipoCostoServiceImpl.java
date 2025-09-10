package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.TipoCostoInput;
import com.example.minimal_prod_backend.dto.TipoCostoResponse;
import com.example.minimal_prod_backend.entity.TipoCosto;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.TipoCostoRepository;
import com.example.minimal_prod_backend.service.TipoCostoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoCostoServiceImpl implements TipoCostoService {

    private final TipoCostoRepository tipoCostoRepository;

    @Override
    public List<TipoCostoResponse> getTiposCosto() {
        return tipoCostoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TipoCostoResponse getTipoCostoById(Integer id) {
        TipoCosto tipoCosto = tipoCostoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoCosto not found with id: " + id));
        return toResponse(tipoCosto);
    }

    @Override
    public TipoCostoResponse createTipoCosto(TipoCostoInput tipoCostoInput) {
        TipoCosto tipoCosto = toEntity(tipoCostoInput);
        return toResponse(tipoCostoRepository.save(tipoCosto));
    }

    @Override
    public TipoCostoResponse updateTipoCosto(Integer id, TipoCostoInput tipoCostoInput) {
        TipoCosto existingTipoCosto = tipoCostoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoCosto not found with id: " + id));
        updateEntityFromInput(tipoCostoInput, existingTipoCosto);
        return toResponse(tipoCostoRepository.save(existingTipoCosto));
    }

    @Override
    public void deleteTipoCosto(Integer id) {
        tipoCostoRepository.deleteById(id);
    }

    private TipoCostoResponse toResponse(TipoCosto entity) {
        if (entity == null) return null;
        TipoCostoResponse dto = new TipoCostoResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setActivo(entity.isActivo());
        dto.setCreadoEn(entity.getCreadoEn());
        return dto;
    }

    private TipoCosto toEntity(TipoCostoInput dto) {
        if (dto == null) return null;
        TipoCosto entity = new TipoCosto();
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setActivo(dto.getActivo());
        return entity;
    }

    private void updateEntityFromInput(TipoCostoInput dto, TipoCosto entity) {
        if (dto == null || entity == null) return;
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setActivo(dto.getActivo());
    }
}
