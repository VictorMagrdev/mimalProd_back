package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.RoleRequest;
import com.example.minimal_prod_backend.entity.Role;
import com.example.minimal_prod_backend.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @PreAuthorize("@customSecurity.hasPermission('ROLE', 'CREATE')")
    public ResponseEntity<Role> createRole(@Valid @RequestBody RoleRequest request) {
        return new ResponseEntity<>(roleService.createRole(request), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("@customSecurity.hasPermission('ROLE', 'READ')")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/{id}")
    @PreAuthorize("@customSecurity.hasPermission('ROLE', 'UPDATE')")
    public Role updateRole(@PathVariable Long id, @Valid @RequestBody RoleRequest request) {
        return roleService.updateRole(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@customSecurity.hasPermission('ROLE', 'DELETE')")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
