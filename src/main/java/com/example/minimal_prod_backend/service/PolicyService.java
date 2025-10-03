package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.PoliticaRequest;
import com.example.minimal_prod_backend.entity.Politica;

import java.util.List;

public interface PolicyService {
    List<Politica> getAllPolicies();

    Politica createPolicy(PoliticaRequest request);

    void deletePolicy(Long id);
}