package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.CentroCostoInput;
import com.example.minimal_prod_backend.dto.CentroCostoResponse;
import com.example.minimal_prod_backend.entity.CentroCosto;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.CentroCostoMapper;
import com.example.minimal_prod_backend.repository.CentroCostoRepository;
import com.example.minimal_prod_backend.service.CentroCostoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CentroCostoServiceImpl implements CentroCostoService {

    private final CentroCostoRepository centroCostoRepository;
    private final CentroCostoMapper centroCostoMapper;

    @Override
    public List<CentroCostoResponse> getCentrosDeCosto() {
        return centroCostoRepository.findAll().stream()
                .map(centroCostoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CentroCostoResponse getCentroDeCostoById(Long id) {
        CentroCosto centroCosto = centroCostoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CentroCosto not found with id: " + id));
        return centroCostoMapper.toResponse(centroCosto);
    }

    @Override
    public CentroCostoResponse createCentroDeCosto(CentroCostoInput centroCostoInput) {
        CentroCosto centroCosto = centroCostoMapper.toEntity(centroCostoInput);
        return centroCostoMapper.toResponse(centroCostoRepository.save(centroCosto));
    }

    @Override
    public CentroCostoResponse updateCentroDeCosto(Long id, CentroCostoInput centroCostoInput) {
        CentroCosto existingCentroCosto = centroCostoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CentroCosto not found with id: " + id));

        existingCentroCosto.setCodigo(centroCostoInput.getCodigo());
        existingCentroCosto.setNombre(centroCostoInput.getNombre());

        return centroCostoMapper.toResponse(centroCostoRepository.save(existingCentroCosto));
    }

    @Override
    public void deleteCentroDeCosto(Long id) {
        if (!centroCostoRepository.existsById(id)) {
            throw new ResourceNotFoundException("CentroCosto not found with id: " + id);
        }
        centroCostoRepository.deleteById(id);
    }
}
