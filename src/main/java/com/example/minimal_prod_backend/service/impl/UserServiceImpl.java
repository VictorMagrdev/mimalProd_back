package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.UserCreateRequest;
import com.example.minimal_prod_backend.dto.UserResponse;
import com.example.minimal_prod_backend.dto.UserUpdateRequest;
import com.example.minimal_prod_backend.entity.Rol;
import com.example.minimal_prod_backend.entity.Usuario;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.exception.UsernameAlreadyExistsException;
import com.example.minimal_prod_backend.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        userRepository.findByUsername(request.getUsername())
                .ifPresent(u -> {
                    throw new UsernameAlreadyExistsException("Username already exists: " + request.getUsername());
                });

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setActivo(true);
        usuario.setRoles(new HashSet<>());

        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            List<Rol> roles = roleRepository.findAllById(request.getRoleIds());
            usuario.getRoles().addAll(roles);
        }

        Usuario savedUsuario = userRepository.save(usuario);
        return userMapper.toUserResponse(savedUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        Usuario usuario = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toUserResponse(usuario);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        Usuario usuario = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (request.getEmail() != null) usuario.setEmail(request.getEmail());
        if (request.getActivo() != null) usuario.setActivo(request.getActivo());

        if (request.getRoleIds() != null) {
            List<Rol> roles = roleRepository.findAllById(request.getRoleIds());
            usuario.setRoles(new HashSet<>(roles));
        }

        Usuario updatedUsuario = userRepository.save(usuario);
        return userMapper.toUserResponse(updatedUsuario);
    }

    @Override
    @Transactional
    public void assignRoleToUser(Long userId, Long roleId) {
        Usuario usuario = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Rol role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
        usuario.getRoles().add(role);
        userRepository.save(usuario);
    }

    @Override
    @Transactional
    public void removeRoleFromUser(Long userId, Long roleId) {
        Usuario usuario = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Rol role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
        usuario.getRoles().remove(role);
        userRepository.save(usuario);
    }

    @Override
    @Transactional
    public void deactivateUser(Long id) {
        Usuario usuario = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        usuario.setActivo(false);
        userRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<RoleResponse> getUserRoles(Long userId) {
        Usuario usuario = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return usuario.getRoles()
                .stream()
                .map(userMapper::toRoleResponse)
                .collect(Collectors.toSet());
    }
}
