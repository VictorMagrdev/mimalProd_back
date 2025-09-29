package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.TipoMovimientoRequest;
import com.example.minimal_prod_backend.dto.TipoMovimientoResponse;
import com.example.minimal_prod_backend.entity.TipoMovimiento;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.TipoMovimientoMapper;
import com.example.minimal_prod_backend.repository.TipoMovimientoRepository;
import com.example.minimal_prod_backend.service.TipoMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoMovimientoServiceImpl implements TipoMovimientoService {

    private final TipoMovimientoRepository tipoMovimientoRepository;
    private final TipoMovimientoMapper mapper;

    @Override
    public List<TipoMovimientoResponse> getTiposMovimiento() {
        return mapper.toResponseList(tipoMovimientoRepository.findAll());
    }

    @Override
    public TipoMovimientoResponse getTipoMovimientoById(Long id) {
        TipoMovimiento tipoMovimiento = tipoMovimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoMovimiento not found with id: " + id));
        return mapper.toResponse(tipoMovimiento);
    }

    @Override
    public TipoMovimientoResponse createTipoMovimiento(TipoMovimientoRequest tipoMovimientoInput) {
        TipoMovimiento tipoMovimiento = mapper.toEntity(tipoMovimientoInput);
        return mapper.toResponse(tipoMovimientoRepository.save(tipoMovimiento));
    }

    @Override
    public TipoMovimientoResponse updateTipoMovimiento(Long id, TipoMovimientoRequest tipoMovimientoInput) {
        TipoMovimiento existingTipoMovimiento = tipoMovimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoMovimiento not found with id: " + id));
        mapper.updateEntityFromInput(tipoMovimientoInput, existingTipoMovimiento);
        return mapper.toResponse(tipoMovimientoRepository.save(existingTipoMovimiento));
    }

    @Override
    public void deleteTipoMovimiento(Long id) {
        tipoMovimientoRepository.deleteById(id);
    }
}
