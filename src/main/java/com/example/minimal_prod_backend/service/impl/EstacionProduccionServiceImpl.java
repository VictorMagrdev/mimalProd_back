package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.EstacionProduccionInput;
import com.example.minimal_prod_backend.dto.EstacionProduccionResponse;
import com.example.minimal_prod_backend.entity.EstacionProduccion;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.EstacionProduccionMapper;
import com.example.minimal_prod_backend.repository.EstacionProduccionRepository;
import com.example.minimal_prod_backend.service.EstacionProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionProduccionServiceImpl implements EstacionProduccionService {

    private final EstacionProduccionRepository estacionProduccionRepository;
    private final EstacionProduccionMapper estacionProduccionMapper;

    @Override
    public List<EstacionProduccionResponse> getEstacionesProduccion() {
        return estacionProduccionMapper.toResponseList(estacionProduccionRepository.findAll());
    }

    @Override
    public EstacionProduccionResponse getEstacionProduccionById(Long id) {
        EstacionProduccion estacionProduccion = estacionProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstacionProduccion not found with id: " + id));
        return estacionProduccionMapper.toResponse(estacionProduccion);
    }

    @Override
    public EstacionProduccionResponse createEstacionProduccion(EstacionProduccionInput estacionProduccionInput) {
        EstacionProduccion estacionProduccion = estacionProduccionMapper.toEntity(estacionProduccionInput);
        estacionProduccion.setCreadoEn(OffsetDateTime.now());
        return estacionProduccionMapper.toResponse(estacionProduccionRepository.save(estacionProduccion));
    }

    @Override
    public EstacionProduccionResponse updateEstacionProduccion(Long id, EstacionProduccionInput estacionProduccionInput) {
        EstacionProduccion existingEstacionProduccion = estacionProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstacionProduccion not found with id: " + id));
        estacionProduccionMapper.updateEntityFromInput(estacionProduccionInput, existingEstacionProduccion);
        return estacionProduccionMapper.toResponse(estacionProduccionRepository.save(existingEstacionProduccion));
    }

    @Override
    public void deleteEstacionProduccion(Long id) {
        estacionProduccionRepository.deleteById(id);
    }
}
