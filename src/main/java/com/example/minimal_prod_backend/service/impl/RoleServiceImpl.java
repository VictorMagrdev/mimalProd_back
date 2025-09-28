package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.RoleRequest;
import com.example.minimal_prod_backend.entity.Rol;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.RoleRepository;
import com.example.minimal_prod_backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Rol createRole(RoleRequest request) {
        Rol role = new Rol();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        return roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rol> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Rol updateRole(Long id, RoleRequest request) {
        Rol role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }
}
