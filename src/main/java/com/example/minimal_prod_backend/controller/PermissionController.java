package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.entity.Permiso;
import com.example.minimal_prod_backend.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG_ROLES', 'READ')")
    public List<Permiso> getAllPermissions() {
        return permissionService.getAllPermissions();
    }
}
