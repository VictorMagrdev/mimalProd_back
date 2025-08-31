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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final RoleRepository roleRepository;
    private final TagRepository tagRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public PolicyServiceImpl(PolicyRepository policyRepository, RoleRepository roleRepository, TagRepository tagRepository, PermissionRepository permissionRepository) {
        this.policyRepository = policyRepository;
        this.roleRepository = roleRepository;
        this.tagRepository = tagRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    @Transactional
    public Policy createPolicy(PolicyRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + request.getRoleId()));
        Tag tag = tagRepository.findById(request.getTagId())
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found: " + request.getTagId()));
        Permission permission = permissionRepository.findById(request.getPermissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + request.getPermissionId()));

        Policy policy = new Policy();
        policy.setRole(role);
        policy.setTag(tag);
        policy.setPermission(permission);
        return policyRepository.save(policy);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    @Transactional
    public void deletePolicy(Long id) {
        if (!policyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Policy not found with id: " + id);
        }
        policyRepository.deleteById(id);
    }
}
