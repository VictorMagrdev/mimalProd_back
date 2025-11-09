package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.TipoBodegaRequest;
import com.example.minimal_prod_backend.dto.Response.TipoBodegaResponse;
import com.example.minimal_prod_backend.entity.TipoBodega;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.TipoBodegaMapper;
import com.example.minimal_prod_backend.repository.TipoBodegaRepository;
import com.example.minimal_prod_backend.service.TipoBodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoBodegaServiceImpl implements TipoBodegaService {

    private final TipoBodegaRepository tipoBodegaRepository;
    private final TipoBodegaMapper mapper;

    @Override
    public List<TipoBodegaResponse> getTiposBodega() {
        return mapper.toResponseList(tipoBodegaRepository.findAll());
    }

    @Override
    public TipoBodegaResponse getTipoBodegaById(Long id) {
        TipoBodega tipoBodega = tipoBodegaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoBodega not found with id: " + id));
        return mapper.toResponse(tipoBodega);
    }

    @Override
    public TipoBodegaResponse createTipoBodega(TipoBodegaRequest tipoBodegaInput) {
        TipoBodega tipoBodega = mapper.toEntity(tipoBodegaInput);
        return mapper.toResponse(tipoBodegaRepository.save(tipoBodega));
    }

    @Override
    public TipoBodegaResponse updateTipoBodega(Long id, TipoBodegaRequest tipoBodegaInput) {
        TipoBodega existingTipoBodega = tipoBodegaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoBodega not found with id: " + id));
        mapper.updateEntityFromInput(tipoBodegaInput, existingTipoBodega);
        return mapper.toResponse(tipoBodegaRepository.save(existingTipoBodega));
    }

    @Override
    public void deleteTipoBodega(Long id) {
        tipoBodegaRepository.deleteById(id);
    }
}
