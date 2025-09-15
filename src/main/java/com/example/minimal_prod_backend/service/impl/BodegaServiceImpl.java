package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.BodegaInput;
import com.example.minimal_prod_backend.dto.BodegaResponse;
import com.example.minimal_prod_backend.dto.TipoBodegaResponse;
import com.example.minimal_prod_backend.entity.Bodega;
import com.example.minimal_prod_backend.entity.TipoBodega;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.BodegaRepository;
import com.example.minimal_prod_backend.repository.TipoBodegaRepository;
import com.example.minimal_prod_backend.service.BodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;
    private final TipoBodegaRepository tipoBodegaRepository;

    @Override
    public List<BodegaResponse> getBodegas() {
        return bodegaRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BodegaResponse getBodegaById(Long id) {
        Bodega bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + id));
        return toResponse(bodega);
    }

    @Override
    public BodegaResponse createBodega(BodegaInput bodegaInput) {
        Bodega bodega = toEntity(bodegaInput);
        return toResponse(bodegaRepository.save(bodega));
    }

    @Override
    public BodegaResponse updateBodega(Long id, BodegaInput bodegaInput) {
        Bodega existingBodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + id));
        updateEntityFromInput(bodegaInput, existingBodega);
        return toResponse(bodegaRepository.save(existingBodega));
    }

    @Override
    public void deleteBodega(Long id) {
        bodegaRepository.deleteById(id);
    }

    private BodegaResponse toResponse(Bodega entity) {
        if (entity == null) return null;
        BodegaResponse dto = new BodegaResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setCreadoEn(entity.getCreadoEn());
        if (entity.getTipo() != null) {
            TipoBodegaResponse tipoDto = new TipoBodegaResponse();
            tipoDto.setId(entity.getTipo().getId());
            tipoDto.setCodigo(entity.getTipo().getCodigo());
            tipoDto.setNombre(entity.getTipo().getNombre());
            tipoDto.setDescripcion(entity.getTipo().getDescripcion());
            dto.setTipo(tipoDto);
        }
        return dto;
    }

    private Bodega toEntity(BodegaInput dto) {
        if (dto == null) return null;
        Bodega entity = new Bodega();
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        if (dto.getIdTipo() != null) {
            TipoBodega tipoBodega = tipoBodegaRepository.findById(dto.getIdTipo())
                    .orElseThrow(() -> new ResourceNotFoundException("TipoBodega not found with id: " + dto.getIdTipo()));
            entity.setTipo(tipoBodega);
        }
        return entity;
    }

    private void updateEntityFromInput(BodegaInput dto, Bodega entity) {
        if (dto == null || entity == null) return;
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        if (dto.getIdTipo() != null) {
            TipoBodega tipoBodega = tipoBodegaRepository.findById(dto.getIdTipo())
                    .orElseThrow(() -> new ResourceNotFoundException("TipoBodega not found with id: " + dto.getIdTipo()));
            entity.setTipo(tipoBodega);
        }
    }
}
