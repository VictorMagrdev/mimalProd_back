package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.FuncionTareaRequest;
import com.example.minimal_prod_backend.dto.FuncionTareaResponse;

import java.util.List;

public interface FuncionTareaService {
    List<FuncionTareaResponse> findAll();

    FuncionTareaResponse findById(Long id);

    FuncionTareaResponse save(FuncionTareaRequest funcionTareaRequest);

    void deleteById(Long id);
}
