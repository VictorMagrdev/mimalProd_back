package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.UnidadConversionInput;
import com.example.minimal_prod_backend.dto.UnidadConversionResponse;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.entity.UnidadConversion;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.UnidadConversionRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.service.UnidadConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnidadConversionServiceImpl implements UnidadConversionService {

    private final UnidadConversionRepository unidadConversionRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UnidadConversionResponse> getUnidadConversiones() {
        return unidadConversionRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UnidadConversionResponse getUnidadConversionById(Long id) {
        UnidadConversion unidadConversion = unidadConversionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadConversion not found with id: " + id));
        return toResponse(unidadConversion);
    }

    @Override
    @Transactional
    public UnidadConversionResponse createUnidadConversion(UnidadConversionInput unidadConversionInput) {
        UnidadConversion unidadConversion = toEntity(unidadConversionInput);
        return toResponse(unidadConversionRepository.save(unidadConversion));
    }

    @Override
    @Transactional
    public UnidadConversionResponse updateUnidadConversion(Long id, UnidadConversionInput unidadConversionInput) {
        UnidadConversion existingUnidadConversion = unidadConversionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadConversion not found with id: " + id));
        updateEntityFromInput(unidadConversionInput, existingUnidadConversion);
        return toResponse(unidadConversionRepository.save(existingUnidadConversion));
    }

    @Override
    public void deleteUnidadConversion(Long id) {
        unidadConversionRepository.deleteById(id);
    }

    private UnidadConversionResponse toResponse(UnidadConversion entity) {
        if (entity == null) return null;
        UnidadConversionResponse dto = new UnidadConversionResponse();
        dto.setId(entity.getId());
        dto.setFactor(entity.getFactor());
        dto.setOrigen(toUnidadMedidaResponse(entity.getOrigen()));
        dto.setDestino(toUnidadMedidaResponse(entity.getDestino()));
        return dto;
    }

    private UnidadMedidaResponse toUnidadMedidaResponse(UnidadMedida entity) {
        if (entity == null) return null;
        UnidadMedidaResponse dto = new UnidadMedidaResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setAbreviatura(entity.getAbreviatura());
        dto.setEsActiva(entity.isEsActiva());
        dto.setEsBase(entity.isEsBase());
        dto.setCreadoEn(entity.getCreadoEn());
        // Tipo is not mapped here to avoid deep nesting, can be added if needed
        return dto;
    }

    private UnidadConversion toEntity(UnidadConversionInput dto) {
        if (dto == null) return null;
        UnidadConversion entity = new UnidadConversion();
        updateEntityFromInput(dto, entity);
        return entity;
    }

    private void updateEntityFromInput(UnidadConversionInput dto, UnidadConversion entity) {
        if (dto == null || entity == null) return;

        entity.setFactor(dto.getFactor());

        if (dto.getIdOrigen() != null) {
            UnidadMedida origen = unidadMedidaRepository.findById(dto.getIdOrigen())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida (origen) not found with id: " + dto.getIdOrigen()));
            entity.setOrigen(origen);
        }

        if (dto.getIdDestino() != null) {
            UnidadMedida destino = unidadMedidaRepository.findById(dto.getIdDestino())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida (destino) not found with id: " + dto.getIdDestino()));
            entity.setDestino(destino);
        }
    }
}
