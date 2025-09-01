package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getAllPermissions();
}
