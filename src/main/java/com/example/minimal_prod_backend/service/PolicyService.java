package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.PolicyRequest;
import com.example.minimal_prod_backend.entity.Policy;

import java.util.List;

public interface PolicyService {
    Policy createPolicy(PolicyRequest request);
    List<Policy> getAllPolicies();
    void deletePolicy(Long id);
}
