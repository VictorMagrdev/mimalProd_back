package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.TipoOrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.TipoOrdenTrabajoResponse;
import com.example.minimal_prod_backend.entity.TipoOrdenTrabajo;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.TipoOrdenTrabajoMapper;
import com.example.minimal_prod_backend.repository.TipoOrdenTrabajoRepository;
import com.example.minimal_prod_backend.service.TipoOrdenTrabajoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoOrdenTrabajoServiceImpl implements TipoOrdenTrabajoService {

    private final TipoOrdenTrabajoRepository tipoOrdenTrabajoRepository;
    private final TipoOrdenTrabajoMapper tipoOrdenTrabajoMapper;

    @Override
    public List<TipoOrdenTrabajoResponse> getTiposOrdenTrabajo() {
        return tipoOrdenTrabajoRepository.findAll().stream()
                .map(tipoOrdenTrabajoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TipoOrdenTrabajoResponse getTipoOrdenTrabajoById(Long id) {
        TipoOrdenTrabajo tipoOrdenTrabajo = tipoOrdenTrabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoOrdenTrabajo not found with id: " + id));
        return tipoOrdenTrabajoMapper.toResponse(tipoOrdenTrabajo);
    }

    @Override
    public TipoOrdenTrabajoResponse createTipoOrdenTrabajo(TipoOrdenTrabajoRequest tipoOrdenTrabajoInput) {
        TipoOrdenTrabajo tipoOrdenTrabajo = tipoOrdenTrabajoMapper.toEntity(tipoOrdenTrabajoInput);
        return tipoOrdenTrabajoMapper.toResponse(tipoOrdenTrabajoRepository.save(tipoOrdenTrabajo));
    }

    @Override
    public TipoOrdenTrabajoResponse updateTipoOrdenTrabajo(Long id, TipoOrdenTrabajoRequest tipoOrdenTrabajoInput) {
        TipoOrdenTrabajo existingTipoOrdenTrabajo = tipoOrdenTrabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoOrdenTrabajo not found with id: " + id));

        tipoOrdenTrabajoMapper.updateEntityFromInput(tipoOrdenTrabajoInput, existingTipoOrdenTrabajo);
        return tipoOrdenTrabajoMapper.toResponse(tipoOrdenTrabajoRepository.save(existingTipoOrdenTrabajo));
    }

    @Override
    public void deleteTipoOrdenTrabajo(Long id) {
        TipoOrdenTrabajo tipoOrdenTrabajo = tipoOrdenTrabajoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoOrdenTrabajo not found with id: " + id));
        tipoOrdenTrabajoRepository.delete(tipoOrdenTrabajo);
    }
}
