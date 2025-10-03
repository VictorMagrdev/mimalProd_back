package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.RolRequest;
import com.example.minimal_prod_backend.entity.Rol;

import java.util.List;

public interface RoleService {
    Rol createRole(RolRequest request);

    List<Rol> getAllRoles();

    Rol updateRole(Long id, RolRequest request);

    void deleteRole(Long id);
}
