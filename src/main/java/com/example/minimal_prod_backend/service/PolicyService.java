package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.PolicyRequest;
import com.example.minimal_prod_backend.entity.Policy;

import java.util.List;

public interface PolicyService {
    List<Policy> getAllPolicies();
    Policy createPolicy(PolicyRequest request);
    void deletePolicy(Long id);
}