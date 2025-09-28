package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.RoleRequest;
import com.example.minimal_prod_backend.entity.Rol;

import java.util.List;

public interface RoleService {
    Rol createRole(RoleRequest request);

    List<Rol> getAllRoles();

    Rol updateRole(Long id, RoleRequest request);

    void deleteRole(Long id);
}
