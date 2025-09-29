package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.PoliticaInput;
import com.example.minimal_prod_backend.entity.Politica;

import java.util.List;

public interface PolicyService {
    List<Politica> getAllPolicies();

    Politica createPolicy(PoliticaInput request);

    void deletePolicy(Long id);
}