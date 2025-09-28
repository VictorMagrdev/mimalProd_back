package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.RolInput;
import com.example.minimal_prod_backend.entity.Rol;

import java.util.List;

public interface RoleService {
    Rol createRole(RolInput request);

    List<Rol> getAllRoles();

    Rol updateRole(Long id, RolInput request);

    void deleteRole(Long id);
}
