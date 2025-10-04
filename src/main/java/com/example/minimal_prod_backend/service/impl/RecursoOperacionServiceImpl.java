package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.RecursoOperacionRequest;
import com.example.minimal_prod_backend.dto.RecursoOperacionResponse;
import com.example.minimal_prod_backend.entity.RecursoOperacion;
import com.example.minimal_prod_backend.mapper.RecursoOperacionMapper;
import com.example.minimal_prod_backend.repository.RecursoOperacionRepository;
import com.example.minimal_prod_backend.service.RecursoOperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecursoOperacionServiceImpl implements RecursoOperacionService {

    @Autowired
    private RecursoOperacionRepository recursoOperacionRepository;

    @Autowired
    private RecursoOperacionMapper recursoOperacionMapper;

    @Override
    public List<RecursoOperacionResponse> findAll() {
        return recursoOperacionRepository.findAll().stream()
                .map(recursoOperacionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RecursoOperacionResponse findById(Long id) {
        RecursoOperacion recursoOperacion = recursoOperacionRepository.findById(id).orElse(null);
        return recursoOperacionMapper.toResponse(recursoOperacion);
    }

    @Override
    public RecursoOperacionResponse save(RecursoOperacionRequest recursoOperacionRequest) {
        RecursoOperacion recursoOperacion = recursoOperacionMapper.toEntity(recursoOperacionRequest);
        return recursoOperacionMapper.toResponse(recursoOperacionRepository.save(recursoOperacion));
    }

    @Override
    public void deleteById(Long id) {
        recursoOperacionRepository.deleteById(id);
    }
}
