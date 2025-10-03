package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.BodegaRequest;
import com.example.minimal_prod_backend.dto.BodegaResponse;
import com.example.minimal_prod_backend.entity.Bodega;
import com.example.minimal_prod_backend.entity.TipoBodega;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.BodegaMapper;
import com.example.minimal_prod_backend.repository.BodegaRepository;
import com.example.minimal_prod_backend.repository.TipoBodegaRepository;
import com.example.minimal_prod_backend.service.BodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;
    private final TipoBodegaRepository tipoBodegaRepository;
    private final BodegaMapper bodegaMapper;

    @Override
    public List<BodegaResponse> getBodegas() {
        return bodegaRepository.findAll().stream()
                .map(bodegaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BodegaResponse getBodegaById(Long id) {
        Bodega bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + id));

        return bodegaMapper.toResponse(bodega);
    }

    @Override
    public BodegaResponse createBodega(BodegaRequest bodegaInput) {
        Bodega bodega = bodegaMapper.toEntity(bodegaInput);

        if (bodegaInput.tipoBodegaId() != null) {
            TipoBodega tipo = tipoBodegaRepository.findById(bodegaInput.tipoBodegaId())
                    .orElseThrow(() -> new ResourceNotFoundException("TipoBodega not found with id: " + bodegaInput.tipoBodegaId()));
            bodega.setTipo(tipo);
        }

        return bodegaMapper.toResponse(bodegaRepository.save(bodega));
    }

    @Override
    public BodegaResponse updateBodega(Long id, BodegaRequest bodegaInput) {
        Bodega existingBodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + id));

        existingBodega.setCodigo(bodegaInput.codigo());
        existingBodega.setNombre(bodegaInput.nombre());
        existingBodega.setDescripcion(bodegaInput.descripcion());

        if (bodegaInput.tipoBodegaId() != null) {
            TipoBodega tipo = tipoBodegaRepository.findById(bodegaInput.tipoBodegaId())
                    .orElseThrow(() -> new ResourceNotFoundException("TipoBodega not found with id: " + bodegaInput.tipoBodegaId()));
            existingBodega.setTipo(tipo);
        }

        return bodegaMapper.toResponse(bodegaRepository.save(existingBodega));
    }

    @Override
    public void deleteBodega(Long id) {
        Bodega bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + id));
        bodegaRepository.save(bodega);
    }
}
