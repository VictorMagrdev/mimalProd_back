package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.UnidadConversionInput;
import com.example.minimal_prod_backend.dto.UnidadConversionResponse;
import com.example.minimal_prod_backend.entity.UnidadConversion;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.UnidadConversionMapper;
import com.example.minimal_prod_backend.repository.UnidadConversionRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.service.UnidadConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadConversionServiceImpl implements UnidadConversionService {

    private final UnidadConversionRepository unidadConversionRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final UnidadConversionMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<UnidadConversionResponse> getUnidadConversiones() {
        return mapper.toResponseList(unidadConversionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UnidadConversionResponse getUnidadConversionById(Long id) {
        UnidadConversion unidadConversion = unidadConversionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadConversion not found with id: " + id));
        return mapper.toResponse(unidadConversion);
    }

    @Override
    @Transactional
    public UnidadConversionResponse createUnidadConversion(UnidadConversionInput unidadConversionInput) {
        UnidadConversion unidadConversion = mapper.toEntity(unidadConversionInput);
        attachRelations(unidadConversionInput, unidadConversion);
        return mapper.toResponse(unidadConversionRepository.save(unidadConversion));
    }

    @Override
    @Transactional
    public UnidadConversionResponse updateUnidadConversion(Long id, UnidadConversionInput unidadConversionInput) {
        UnidadConversion existingUnidadConversion = unidadConversionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadConversion not found with id: " + id));
        mapper.updateEntityFromInput(unidadConversionInput, existingUnidadConversion);
        attachRelations(unidadConversionInput, existingUnidadConversion);
        return mapper.toResponse(unidadConversionRepository.save(existingUnidadConversion));
    }

    @Override
    public void deleteUnidadConversion(Long id) {
        unidadConversionRepository.deleteById(id);
    }

    private void attachRelations(UnidadConversionInput dto, UnidadConversion entity) {
        if (dto.getIdOrigen() != null) {
            entity.setOrigen(unidadMedidaRepository.findById(dto.getIdOrigen())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida (origen) not found with id: " + dto.getIdOrigen())));
        }
        if (dto.getIdDestino() != null) {
            entity.setDestino(unidadMedidaRepository.findById(dto.getIdDestino())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida (destino) not found with id: " + dto.getIdDestino())));
        }
    }
}
