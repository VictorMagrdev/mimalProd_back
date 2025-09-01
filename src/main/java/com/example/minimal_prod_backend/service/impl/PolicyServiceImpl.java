package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.PolicyRequest;
import com.example.minimal_prod_backend.entity.Permission;
import com.example.minimal_prod_backend.entity.Policy;
import com.example.minimal_prod_backend.entity.Role;
import com.example.minimal_prod_backend.entity.Tag;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.PermissionRepository;
import com.example.minimal_prod_backend.repository.PolicyRepository;
import com.example.minimal_prod_backend.repository.RoleRepository;
import com.example.minimal_prod_backend.repository.TagRepository;
import com.example.minimal_prod_backend.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final RoleRepository roleRepository;
    private final TagRepository tagRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    public Policy createPolicy(PolicyRequest request) {
        Role role = roleRepository.findById(request.roleId()).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        Tag tag = tagRepository.findById(request.tagId()).orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
        Permission permission = permissionRepository.findById(request.permissionId()).orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        Policy policy = Policy.builder()
                .role(role)
                .tag(tag)
                .permission(permission)
                .build();

        return policyRepository.save(policy);
    }

    @Override
    public void deletePolicy(Long id) {
        if (!policyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Policy not found");
        }
        policyRepository.deleteById(id);
    }
}