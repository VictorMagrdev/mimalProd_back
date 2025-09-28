package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.PolicyRequest;
import com.example.minimal_prod_backend.entity.Politica;

import java.util.List;

public interface PolicyService {
    List<Politica> getAllPolicies();

    Politica createPolicy(PolicyRequest request);

    void deletePolicy(Long id);
}