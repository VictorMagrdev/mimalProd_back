package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.RoleResponse;
import com.example.minimal_prod_backend.dto.UserCreateRequest;
import com.example.minimal_prod_backend.dto.UserResponse;
import com.example.minimal_prod_backend.dto.UserUpdateRequest;
import com.example.minimal_prod_backend.entity.Rol;
import com.example.minimal_prod_backend.entity.Usuario;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.exception.UsernameAlreadyExistsException;
import com.example.minimal_prod_backend.repository.RoleRepository;
import com.example.minimal_prod_backend.repository.UserRepository;
import com.example.minimal_prod_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        userRepository.findByUsername(request.getUsername()).ifPresent(u -> {
            throw new UsernameAlreadyExistsException("Username already exists: " + request.getUsername());
        });

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            List<Rol> roles = roleRepository.findAllById(request.getRoleIds());
            usuario.setRoles(new HashSet<>(roles));
        }

        Usuario savedUsuario = userRepository.save(usuario);
        return new UserResponse(savedUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        Usuario usuario = userRepository.findWithRolesById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        usuario.getRoles().forEach(r -> System.out.println(r.getName()));
        return new UserResponse(usuario);
    }


    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        Usuario usuario = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (request.getEmail() != null) {
            usuario.setEmail(request.getEmail());
        }
        if (request.isActive()) {
            usuario.setActive(true);
        }

        if (request.getRoleIds() != null) {
            List<Rol> roles = roleRepository.findAllById(request.getRoleIds());
            usuario.setRoles(new HashSet<>(roles));
        }

        Usuario updatedUsuario = userRepository.save(usuario);
        return new UserResponse(updatedUsuario);
    }

    @Override
    @Transactional
    public void assignRoleToUser(Long userId, Long roleId) {
        Usuario usuario = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Rol role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
        usuario.getRoles().add(role);
        userRepository.save(usuario);
    }

    @Override
    @Transactional
    public void removeRoleFromUser(Long userId, Long roleId) {
        Usuario usuario = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Rol role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
        usuario.getRoles().remove(role);
        userRepository.save(usuario);
    }

    @Override
    @Transactional
    public void deactivateUser(Long id) {
        Usuario usuario = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        usuario.setActive(false);
        userRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<RoleResponse> getUserRoles(Long userId) {
        Usuario usuario = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return usuario.getRoles().stream()
                .map(RoleResponse::new)
                .collect(Collectors.toSet());
    }

}
