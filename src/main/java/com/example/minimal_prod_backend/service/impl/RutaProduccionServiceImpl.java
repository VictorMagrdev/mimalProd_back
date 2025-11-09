package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.RutaProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.RutaProduccionResponse;
import com.example.minimal_prod_backend.entity.RutaProduccion;
import com.example.minimal_prod_backend.mapper.RutaProduccionMapper;
import com.example.minimal_prod_backend.repository.RutaProduccionRepository;
import com.example.minimal_prod_backend.service.RutaProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutaProduccionServiceImpl implements RutaProduccionService {

    @Autowired
    private RutaProduccionRepository rutaProduccionRepository;

    @Autowired
    private RutaProduccionMapper rutaProduccionMapper;

    @Override
    public List<RutaProduccionResponse> findAll() {
        return rutaProduccionRepository.findAll().stream()
                .map(rutaProduccionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RutaProduccionResponse findById(Long id) {
        RutaProduccion rutaProduccion = rutaProduccionRepository.findById(id).orElse(null);
        return rutaProduccionMapper.toResponse(rutaProduccion);
    }

    @Override
    public RutaProduccionResponse save(RutaProduccionRequest rutaProduccionRequest) {
        RutaProduccion rutaProduccion = rutaProduccionMapper.toEntity(rutaProduccionRequest);
        return rutaProduccionMapper.toResponse(rutaProduccionRepository.save(rutaProduccion));
    }

    @Override
    public void deleteById(Long id) {
        rutaProduccionRepository.deleteById(id);
    }
}
