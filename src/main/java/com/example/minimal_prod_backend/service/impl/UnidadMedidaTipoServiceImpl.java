package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.UnidadMedidaTipoInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaTipoResponse;
import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.UnidadMedidaTipoRepository;
import com.example.minimal_prod_backend.service.UnidadMedidaTipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnidadMedidaTipoServiceImpl implements UnidadMedidaTipoService {

    private final UnidadMedidaTipoRepository unidadMedidaTipoRepository;

    @Override
    public List<UnidadMedidaTipoResponse> getUnidadMedidaTipos() {
        return unidadMedidaTipoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UnidadMedidaTipoResponse getUnidadMedidaTipoById(Long id) {
        UnidadMedidaTipo unidadMedidaTipo = unidadMedidaTipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedidaTipo not found with id: " + id));
        return toResponse(unidadMedidaTipo);
    }

    @Override
    public UnidadMedidaTipoResponse createUnidadMedidaTipo(UnidadMedidaTipoInput unidadMedidaTipoInput) {
        UnidadMedidaTipo unidadMedidaTipo = toEntity(unidadMedidaTipoInput);
        return toResponse(unidadMedidaTipoRepository.save(unidadMedidaTipo));
    }

    @Override
    public UnidadMedidaTipoResponse updateUnidadMedidaTipo(Long id, UnidadMedidaTipoInput unidadMedidaTipoInput) {
        UnidadMedidaTipo existingUnidadMedidaTipo = unidadMedidaTipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedidaTipo not found with id: " + id));
        updateEntityFromInput(unidadMedidaTipoInput, existingUnidadMedidaTipo);
        return toResponse(unidadMedidaTipoRepository.save(existingUnidadMedidaTipo));
    }

    @Override
    public void deleteUnidadMedidaTipo(Long id) {
        unidadMedidaTipoRepository.deleteById(id);
    }

    private UnidadMedidaTipoResponse toResponse(UnidadMedidaTipo entity) {
        if (entity == null) return null;
        UnidadMedidaTipoResponse dto = new UnidadMedidaTipoResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setCreadoEn(entity.getCreadoEn());
        return dto;
    }

    private UnidadMedidaTipo toEntity(UnidadMedidaTipoInput dto) {
        if (dto == null) return null;
        UnidadMedidaTipo entity = new UnidadMedidaTipo();
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }

    private void updateEntityFromInput(UnidadMedidaTipoInput dto, UnidadMedidaTipo entity) {
        if (dto == null || entity == null) return;
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
    }
}
