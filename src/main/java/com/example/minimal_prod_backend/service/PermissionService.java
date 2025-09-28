package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.Permiso;

import java.util.List;

public interface PermissionService {
    List<Permiso> getAllPermissions();
}
