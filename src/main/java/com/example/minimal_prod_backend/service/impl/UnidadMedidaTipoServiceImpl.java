package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.UnidadMedidaTipoRequest;
import com.example.minimal_prod_backend.dto.Response.UnidadMedidaTipoResponse;
import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.UnidadMedidaTipoMapper;
import com.example.minimal_prod_backend.repository.UnidadMedidaTipoRepository;
import com.example.minimal_prod_backend.service.UnidadMedidaTipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadMedidaTipoServiceImpl implements UnidadMedidaTipoService {

    private final UnidadMedidaTipoRepository unidadMedidaTipoRepository;
    private final UnidadMedidaTipoMapper mapper;

    @Override
    public List<UnidadMedidaTipoResponse> getUnidadMedidaTipos() {
        return mapper.toResponseList(unidadMedidaTipoRepository.findAll());
    }

    @Override
    public UnidadMedidaTipoResponse getUnidadMedidaTipoById(Long id) {
        UnidadMedidaTipo unidadMedidaTipo = unidadMedidaTipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedidaTipo not found with id: " + id));
        return mapper.toResponse(unidadMedidaTipo);
    }

    @Override
    public UnidadMedidaTipoResponse createUnidadMedidaTipo(UnidadMedidaTipoRequest unidadMedidaTipoInput) {
        UnidadMedidaTipo unidadMedidaTipo = mapper.toEntity(unidadMedidaTipoInput);
        return mapper.toResponse(unidadMedidaTipoRepository.save(unidadMedidaTipo));
    }

    @Override
    public UnidadMedidaTipoResponse updateUnidadMedidaTipo(Long id, UnidadMedidaTipoRequest unidadMedidaTipoInput) {
        UnidadMedidaTipo existingUnidadMedidaTipo = unidadMedidaTipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnidadMedidaTipo not found with id: " + id));
        mapper.updateEntityFromInput(unidadMedidaTipoInput, existingUnidadMedidaTipo);
        return mapper.toResponse(unidadMedidaTipoRepository.save(existingUnidadMedidaTipo));
    }

    @Override
    public void deleteUnidadMedidaTipo(Long id) {
        unidadMedidaTipoRepository.deleteById(id);
    }
}
