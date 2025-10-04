package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.OperacionOrdenEstadoRequest;
import com.example.minimal_prod_backend.dto.OperacionOrdenEstadoResponse;
import com.example.minimal_prod_backend.entity.OperacionOrdenEstado;
import com.example.minimal_prod_backend.mapper.OperacionOrdenEstadoMapper;
import com.example.minimal_prod_backend.repository.OperacionOrdenEstadoRepository;
import com.example.minimal_prod_backend.service.OperacionOrdenEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperacionOrdenEstadoServiceImpl implements OperacionOrdenEstadoService {

    @Autowired
    private OperacionOrdenEstadoRepository operacionOrdenEstadoRepository;

    @Autowired
    private OperacionOrdenEstadoMapper operacionOrdenEstadoMapper;

    @Override
    public List<OperacionOrdenEstadoResponse> findAll() {
        return operacionOrdenEstadoRepository.findAll().stream()
                .map(operacionOrdenEstadoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OperacionOrdenEstadoResponse findById(Long id) {
        OperacionOrdenEstado operacionOrdenEstado = operacionOrdenEstadoRepository.findById(id).orElse(null);
        return operacionOrdenEstadoMapper.toResponse(operacionOrdenEstado);
    }

    @Override
    public OperacionOrdenEstadoResponse save(OperacionOrdenEstadoRequest operacionOrdenEstadoRequest) {
        OperacionOrdenEstado operacionOrdenEstado = operacionOrdenEstadoMapper.toEntity(operacionOrdenEstadoRequest);
        return operacionOrdenEstadoMapper.toResponse(operacionOrdenEstadoRepository.save(operacionOrdenEstado));
    }

    @Override
    public void deleteById(Long id) {
        operacionOrdenEstadoRepository.deleteById(id);
    }
}
