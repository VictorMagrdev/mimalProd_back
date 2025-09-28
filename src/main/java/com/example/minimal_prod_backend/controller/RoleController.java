package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.RolInput;
import com.example.minimal_prod_backend.entity.Rol;
import com.example.minimal_prod_backend.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG_ROLES', 'CREATE')")
    public ResponseEntity<Rol> createRole(@Valid @RequestBody RolInput request) {
        return new ResponseEntity<>(roleService.createRole(request), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG_ROLES', 'READ')")
    public List<Rol> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/{id}")
    @PreAuthorize("@customSecurity.hasPermission('TAG_ROLES', 'UPDATE')")
    public Rol updateRole(@PathVariable Long id, @Valid @RequestBody RolInput request) {
        return roleService.updateRole(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@customSecurity.hasPermission('TAG_ROLES', 'DELETE')")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
