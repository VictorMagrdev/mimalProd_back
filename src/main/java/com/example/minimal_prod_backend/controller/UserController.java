package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.UserCreateRequest;
import com.example.minimal_prod_backend.dto.UserResponse;
import com.example.minimal_prod_backend.dto.UserUpdateRequest;
import com.example.minimal_prod_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG_USERS', 'CREATE')")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserResponse createdUser = userService.createUser(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG_USERS', 'READ')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("@customSecurity.hasPermission('TAG_USERS', 'READ')")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@customSecurity.hasPermission('TAG_USERS', 'UPDATE')")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @PostMapping("/{userId}/roles")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@customSecurity.hasPermission('TAG_USERS', 'UPDATE')")
    public void assignRoleToUser(@PathVariable Long userId, @RequestBody Map<String, Long> payload) {
        userService.assignRoleToUser(userId, payload.get("roleId"));
    }

    @GetMapping("/{userId}/roles")
    @PreAuthorize("@customSecurity.hasPermission('TAG_USERS', 'READ')")
    public Set<RoleResponse> getUserRoles(@PathVariable Long userId) {
        return userService.getUserRoles(userId);
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@customSecurity.hasPermission('TAG_USERS', 'UPDATE')")
    public void removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.removeRoleFromUser(userId, roleId);
    }

    @PostMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@customSecurity.hasPermission('TAG_USERS', 'UPDATE')")
    public ResponseEntity<String> deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok("Usuario desactivado");
    }

}
