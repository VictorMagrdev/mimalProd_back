package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.FuncionTareaRequest;
import com.example.minimal_prod_backend.dto.FuncionTareaResponse;
import com.example.minimal_prod_backend.entity.FuncionTarea;
import com.example.minimal_prod_backend.mapper.FuncionTareaMapper;
import com.example.minimal_prod_backend.repository.FuncionTareaRepository;
import com.example.minimal_prod_backend.service.FuncionTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionTareaServiceImpl implements FuncionTareaService {

    @Autowired
    private FuncionTareaRepository funcionTareaRepository;

    @Autowired
    private FuncionTareaMapper funcionTareaMapper;

    @Override
    public List<FuncionTareaResponse> findAll() {
        return funcionTareaRepository.findAll().stream()
                .map(funcionTareaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FuncionTareaResponse findById(Long id) {
        FuncionTarea funcionTarea = funcionTareaRepository.findById(id).orElse(null);
        return funcionTareaMapper.toResponse(funcionTarea);
    }

    @Override
    public FuncionTareaResponse save(FuncionTareaRequest funcionTareaRequest) {
        FuncionTarea funcionTarea = funcionTareaMapper.toEntity(funcionTareaRequest);
        return funcionTareaMapper.toResponse(funcionTareaRepository.save(funcionTarea));
    }

    @Override
    public void deleteById(Long id) {
        funcionTareaRepository.deleteById(id);
    }
}
