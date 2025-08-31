package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.UserCreateRequest;
import com.example.minimal_prod_backend.dto.UserResponse;
import com.example.minimal_prod_backend.dto.UserUpdateRequest;
import com.example.minimal_prod_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("@customSecurity.hasPermission('USER', 'CREATE')")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserResponse createdUser = userService.createUser(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("@customSecurity.hasPermission('USER', 'READ')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("@customSecurity.hasPermission('USER', 'READ')")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@customSecurity.hasPermission('USER', 'UPDATE')")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@customSecurity.hasPermission('USER', 'DELETE')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{userId}/roles")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@customSecurity.hasPermission('USER', 'UPDATE')")
    public void assignRoleToUser(@PathVariable Long userId, @RequestBody Map<String, Long> payload) {
        userService.assignRoleToUser(userId, payload.get("roleId"));
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@customSecurity.hasPermission('USER', 'UPDATE')")
    public void removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.removeRoleFromUser(userId, roleId);
    }
}
