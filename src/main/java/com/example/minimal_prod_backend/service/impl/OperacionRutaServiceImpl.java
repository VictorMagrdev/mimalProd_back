package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.OperacionRutaRequest;
import com.example.minimal_prod_backend.dto.OperacionRutaResponse;
import com.example.minimal_prod_backend.entity.OperacionRuta;
import com.example.minimal_prod_backend.mapper.OperacionRutaMapper;
import com.example.minimal_prod_backend.repository.OperacionRutaRepository;
import com.example.minimal_prod_backend.service.OperacionRutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperacionRutaServiceImpl implements OperacionRutaService {

    @Autowired
    private OperacionRutaRepository operacionRutaRepository;

    @Autowired
    private OperacionRutaMapper operacionRutaMapper;

    @Override
    public List<OperacionRutaResponse> findAll() {
        return operacionRutaRepository.findAll().stream()
                .map(operacionRutaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OperacionRutaResponse findById(Long id) {
        OperacionRuta operacionRuta = operacionRutaRepository.findById(id).orElse(null);
        return operacionRutaMapper.toResponse(operacionRuta);
    }

    @Override
    public OperacionRutaResponse save(OperacionRutaRequest operacionRutaRequest) {
        OperacionRuta operacionRuta = operacionRutaMapper.toEntity(operacionRutaRequest);
        return operacionRutaMapper.toResponse(operacionRutaRepository.save(operacionRuta));
    }

    @Override
    public void deleteById(Long id) {
        operacionRutaRepository.deleteById(id);
    }
}
