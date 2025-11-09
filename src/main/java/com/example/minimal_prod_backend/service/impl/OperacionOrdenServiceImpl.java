package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.OperacionOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.OperacionOrdenResponse;
import com.example.minimal_prod_backend.entity.OperacionOrden;
import com.example.minimal_prod_backend.mapper.OperacionOrdenMapper;
import com.example.minimal_prod_backend.repository.OperacionOrdenRepository;
import com.example.minimal_prod_backend.service.OperacionOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperacionOrdenServiceImpl implements OperacionOrdenService {

    @Autowired
    private OperacionOrdenRepository operacionOrdenRepository;

    @Autowired
    private OperacionOrdenMapper operacionOrdenMapper;

    @Override
    public List<OperacionOrdenResponse> findAll() {
        return operacionOrdenRepository.findAll().stream()
                .map(operacionOrdenMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OperacionOrdenResponse findById(Long id) {
        OperacionOrden operacionOrden = operacionOrdenRepository.findById(id).orElse(null);
        return operacionOrdenMapper.toResponse(operacionOrden);
    }

    @Override
    public OperacionOrdenResponse save(OperacionOrdenRequest operacionOrdenRequest) {
        OperacionOrden operacionOrden = operacionOrdenMapper.toEntity(operacionOrdenRequest);
        return operacionOrdenMapper.toResponse(operacionOrdenRepository.save(operacionOrden));
    }

    @Override
    public void deleteById(Long id) {
        operacionOrdenRepository.deleteById(id);
    }
}
