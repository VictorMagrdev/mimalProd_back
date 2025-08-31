package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.PolicyRequest;
import com.example.minimal_prod_backend.entity.ObjectEntity;
import com.example.minimal_prod_backend.entity.Permission;
import com.example.minimal_prod_backend.entity.Policy;
import com.example.minimal_prod_backend.entity.Role;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.ObjectEntityRepository;
import com.example.minimal_prod_backend.repository.PermissionRepository;
import com.example.minimal_prod_backend.repository.PolicyRepository;
import com.example.minimal_prod_backend.repository.RoleRepository;
import com.example.minimal_prod_backend.service.PolicyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final RoleRepository roleRepository;
    private final ObjectEntityRepository objectEntityRepository;
    private final PermissionRepository permissionRepository;

    public PolicyServiceImpl(PolicyRepository policyRepository, RoleRepository roleRepository, ObjectEntityRepository objectEntityRepository, PermissionRepository permissionRepository) {
        this.policyRepository = policyRepository;
        this.roleRepository = roleRepository;
        this.objectEntityRepository = objectEntityRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    @Transactional
    public Policy createPolicy(PolicyRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + request.getRoleId()));
        ObjectEntity object = objectEntityRepository.findById(request.getObjectId())
                .orElseThrow(() -> new ResourceNotFoundException("ObjectEntity not found: " + request.getObjectId()));
        Permission permission = permissionRepository.findById(request.getPermissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + request.getPermissionId()));

        Policy policy = new Policy();
        policy.setRole(role);
        policy.setObjectEntity(object);
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
