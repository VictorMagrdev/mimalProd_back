package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.TipoProductoRequest;
import com.example.minimal_prod_backend.dto.Response.TipoProductoResponse;
import com.example.minimal_prod_backend.entity.TipoProducto;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.TipoProductoMapper;
import com.example.minimal_prod_backend.repository.TipoProductoRepository;
import com.example.minimal_prod_backend.service.TipoProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoProductoServiceImpl implements TipoProductoService {

    private final TipoProductoRepository tipoProductoRepository;
    private final TipoProductoMapper tipoProductoMapper;

    @Override
    public List<TipoProductoResponse> getTiposProducto() {
        return tipoProductoRepository.findAll().stream()
                .map(tipoProductoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TipoProductoResponse getTipoProductoById(Long id) {
        TipoProducto tipoProducto = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoProducto not found with id: " + id));
        return tipoProductoMapper.toResponse(tipoProducto);
    }

    @Override
    public TipoProductoResponse createTipoProducto(TipoProductoRequest tipoProductoInput) {
        TipoProducto tipoProducto = tipoProductoMapper.toEntity(tipoProductoInput);
        return tipoProductoMapper.toResponse(tipoProductoRepository.save(tipoProducto));
    }

    @Override
    public TipoProductoResponse updateTipoProducto(Long id, TipoProductoRequest tipoProductoInput) {
        TipoProducto existingTipoProducto = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoProducto not found with id: " + id));

        tipoProductoMapper.updateEntityFromInput(tipoProductoInput, existingTipoProducto);
        return tipoProductoMapper.toResponse(tipoProductoRepository.save(existingTipoProducto));
    }

    @Override
    public void deleteTipoProducto(Long id) {
        TipoProducto tipoProducto = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoProducto not found with id: " + id));
        tipoProductoRepository.delete(tipoProducto);
    }
}
