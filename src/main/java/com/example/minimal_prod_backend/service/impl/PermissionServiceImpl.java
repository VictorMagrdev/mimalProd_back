package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.Permission;
import com.example.minimal_prod_backend.repository.PermissionRepository;
import com.example.minimal_prod_backend.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}
