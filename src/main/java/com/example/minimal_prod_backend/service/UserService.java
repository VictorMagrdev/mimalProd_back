package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Response.RolResponse;
import com.example.minimal_prod_backend.dto.Request.UserCreateRequest;
import com.example.minimal_prod_backend.dto.Response.UserResponse;
import com.example.minimal_prod_backend.dto.Request.UserUpdateRequest;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserResponse createUser(UserCreateRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserUpdateRequest request);

    void assignRoleToUser(Long userId, Long roleId);

    void removeRoleFromUser(Long userId, Long roleId);

    void deactivateUser(Long id);

    Set<RolResponse> getUserRoles(Long userId);
}
