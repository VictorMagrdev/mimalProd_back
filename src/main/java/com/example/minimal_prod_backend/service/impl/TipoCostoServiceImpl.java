package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.TipoCostoInput;
import com.example.minimal_prod_backend.dto.TipoCostoResponse;
import com.example.minimal_prod_backend.entity.TipoCosto;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.TipoCostoMapper;
import com.example.minimal_prod_backend.repository.TipoCostoRepository;
import com.example.minimal_prod_backend.service.TipoCostoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCostoServiceImpl implements TipoCostoService {

    private final TipoCostoRepository tipoCostoRepository;
    private final TipoCostoMapper mapper;

    @Override
    public List<TipoCostoResponse> getTiposCosto() {
        return mapper.toResponseList(tipoCostoRepository.findAll());
    }

    @Override
    public TipoCostoResponse getTipoCostoById(Long id) {
        TipoCosto tipoCosto = tipoCostoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoCosto not found with id: " + id));
        return mapper.toResponse(tipoCosto);
    }

    @Override
    public TipoCostoResponse createTipoCosto(TipoCostoInput tipoCostoInput) {
        TipoCosto tipoCosto = mapper.toEntity(tipoCostoInput);
        return mapper.toResponse(tipoCostoRepository.save(tipoCosto));
    }

    @Override
    public TipoCostoResponse updateTipoCosto(Long id, TipoCostoInput tipoCostoInput) {
        TipoCosto existingTipoCosto = tipoCostoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoCosto not found with id: " + id));
        mapper.updateEntityFromInput(tipoCostoInput, existingTipoCosto);
        return mapper.toResponse(tipoCostoRepository.save(existingTipoCosto));
    }

    @Override
    public void deleteTipoCosto(Long id) {
        tipoCostoRepository.deleteById(id);
    }
}
