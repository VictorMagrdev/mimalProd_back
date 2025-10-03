package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.PoliticaRequest;
import com.example.minimal_prod_backend.entity.Permiso;
import com.example.minimal_prod_backend.entity.Politica;
import com.example.minimal_prod_backend.entity.Rol;
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
    public List<Politica> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    public Politica createPolicy(PoliticaRequest request) {
        Rol role = roleRepository.findById(request.rolId()).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        Tag tag = tagRepository.findById(request.tagId()).orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
        Permiso permiso = permissionRepository.findById(request.permisoId()).orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        Politica politica = Politica.builder()
                .rol(role)
                .tag(tag)
                .permiso(permiso)
                .build();

        return policyRepository.save(politica);
    }

    @Override
    public void deletePolicy(Long id) {
        if (!policyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Policy not found");
        }
        policyRepository.deleteById(id);
    }
}