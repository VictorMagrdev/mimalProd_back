package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.UnidadMedidaRequest;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.UnidadMedidaMapper;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaTipoRepository;
import com.example.minimal_prod_backend.service.UnidadMedidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    private final UnidadMedidaRepository unidadMedidaRepository;
    private final UnidadMedidaTipoRepository unidadMedidaTipoRepository;
    private final UnidadMedidaMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<UnidadMedidaResponse> getUnidadesMedida() {
        return mapper.toResponseList(unidadMedidaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UnidadMedidaResponse getUnidadMedidaById(Long id) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + id));
        return mapper.toResponse(unidadMedida);
    }

    @Override
    @Transactional
    public UnidadMedidaResponse createUnidadMedida(UnidadMedidaRequest unidadMedidaInput) {
        UnidadMedida unidadMedida = mapper.toEntity(unidadMedidaInput);
        attachTipo(unidadMedidaInput, unidadMedida);
        return mapper.toResponse(unidadMedidaRepository.save(unidadMedida));
    }

    @Override
    @Transactional
    public UnidadMedidaResponse updateUnidadMedida(Long id, UnidadMedidaRequest unidadMedidaInput) {
        UnidadMedida existingUnidadMedida = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + id));
        mapper.updateEntityFromInput(unidadMedidaInput, existingUnidadMedida);
        attachTipo(unidadMedidaInput, existingUnidadMedida);
        return mapper.toResponse(unidadMedidaRepository.save(existingUnidadMedida));
    }

    @Override
    public void deleteUnidadMedida(Long id) {
        unidadMedidaRepository.deleteById(id);
    }

    private void attachTipo(UnidadMedidaRequest dto, UnidadMedida entity) {
        if (dto.unidadMedidaTipoId() != null) {
            UnidadMedidaTipo tipo = unidadMedidaTipoRepository.findById(dto.unidadMedidaTipoId())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedidaTipo not found with id: " + dto.unidadMedidaTipoId()));
            entity.setTipo(tipo);
        }
    }
}
