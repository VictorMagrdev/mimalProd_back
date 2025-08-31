package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.RoleRequest;
import com.example.minimal_prod_backend.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(RoleRequest request);
    List<Role> getAllRoles();
    Role updateRole(Long id, RoleRequest request);
    void deleteRole(Long id);
}
