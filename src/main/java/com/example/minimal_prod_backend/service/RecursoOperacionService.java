package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.RecursoOperacionRequest;
import com.example.minimal_prod_backend.dto.RecursoOperacionResponse;

import java.util.List;

public interface RecursoOperacionService {
    List<RecursoOperacionResponse> findAll();

    RecursoOperacionResponse findById(Long id);

    RecursoOperacionResponse save(RecursoOperacionRequest recursoOperacionRequest);

    void deleteById(Long id);
}
