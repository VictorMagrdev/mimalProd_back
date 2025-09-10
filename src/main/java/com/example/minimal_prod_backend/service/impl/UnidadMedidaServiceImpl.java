package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.UnidadMedidaInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.dto.UnidadMedidaTipoResponse;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaTipoRepository;
import com.example.minimal_prod_backend.service.UnidadMedidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    private final UnidadMedidaRepository unidadMedidaRepository;
    private final UnidadMedidaTipoRepository unidadMedidaTipoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UnidadMedidaResponse> getUnidadesMedida() {
        return unidadMedidaRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UnidadMedidaResponse getUnidadMedidaById(Integer id) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + id));
        return toResponse(unidadMedida);
    }

    @Override
    @Transactional
    public UnidadMedidaResponse createUnidadMedida(UnidadMedidaInput unidadMedidaInput) {
        UnidadMedida unidadMedida = toEntity(unidadMedidaInput);
        return toResponse(unidadMedidaRepository.save(unidadMedida));
    }

    @Override
    @Transactional
    public UnidadMedidaResponse updateUnidadMedida(Integer id, UnidadMedidaInput unidadMedidaInput) {
        UnidadMedida existingUnidadMedida = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + id));
        updateEntityFromInput(unidadMedidaInput, existingUnidadMedida);
        return toResponse(unidadMedidaRepository.save(existingUnidadMedida));
    }

    @Override
    public void deleteUnidadMedida(Integer id) {
        unidadMedidaRepository.deleteById(id);
    }

    private UnidadMedidaResponse toResponse(UnidadMedida entity) {
        if (entity == null) return null;
        UnidadMedidaResponse dto = new UnidadMedidaResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setAbreviatura(entity.getAbreviatura());
        dto.setEsActiva(entity.isEsActiva());
        dto.setEsBase(entity.isEsBase());
        dto.setCreadoEn(entity.getCreadoEn());

        if (entity.getTipo() != null) {
            UnidadMedidaTipoResponse tipoDto = new UnidadMedidaTipoResponse();
            tipoDto.setId(entity.getTipo().getId());
            tipoDto.setCodigo(entity.getTipo().getCodigo());
            tipoDto.setNombre(entity.getTipo().getNombre());
            tipoDto.setDescripcion(entity.getTipo().getDescripcion());
            tipoDto.setCreadoEn(entity.getTipo().getCreadoEn());
            dto.setTipo(tipoDto);
        }

        return dto;
    }

    private UnidadMedida toEntity(UnidadMedidaInput dto) {
        if (dto == null) return null;
        UnidadMedida entity = new UnidadMedida();
        updateEntityFromInput(dto, entity);
        return entity;
    }

    private void updateEntityFromInput(UnidadMedidaInput dto, UnidadMedida entity) {
        if (dto == null || entity == null) return;

        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setAbreviatura(dto.getAbreviatura());
        entity.setEsActiva(dto.getEsActiva());
        entity.setEsBase(dto.getEsBase());

        if (dto.getIdTipo() != null) {
            UnidadMedidaTipo tipo = unidadMedidaTipoRepository.findById(dto.getIdTipo())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedidaTipo not found with id: " + dto.getIdTipo()));
            entity.setTipo(tipo);
        }
    }
}
