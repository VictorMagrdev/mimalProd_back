package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.BodegaInput;
import com.example.minimal_prod_backend.dto.BodegaResponse;
import com.example.minimal_prod_backend.entity.Bodega;
import com.example.minimal_prod_backend.entity.TipoBodega;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
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
    public BodegaResponse createBodega(BodegaInput bodegaInput) {
        Bodega bodega = bodegaMapper.toEntity(bodegaInput);

        if (bodegaInput.getIdTipo() != null) {
            TipoBodega tipo = tipoBodegaRepository.findById(bodegaInput.getIdTipo())
                    .orElseThrow(() -> new ResourceNotFoundException("TipoBodega not found with id: " + bodegaInput.getIdTipo()));
            bodega.setTipo(tipo);
        }

        return bodegaMapper.toResponse(bodegaRepository.save(bodega));
    }

    @Override
    public BodegaResponse updateBodega(Long id, BodegaInput bodegaInput) {
        Bodega existingBodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + id));

        existingBodega.setCodigo(bodegaInput.getCodigo());
        existingBodega.setNombre(bodegaInput.getNombre());
        existingBodega.setDescripcion(bodegaInput.getDescripcion());

        if (bodegaInput.getIdTipo() != null) {
            TipoBodega tipo = tipoBodegaRepository.findById(bodegaInput.getIdTipo())
                    .orElseThrow(() -> new ResourceNotFoundException("TipoBodega not found with id: " + bodegaInput.getIdTipo()));
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
