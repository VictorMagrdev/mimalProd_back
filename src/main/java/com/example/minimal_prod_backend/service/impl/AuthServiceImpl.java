package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.LoginRequest;
import com.example.minimal_prod_backend.dto.LoginResponse;
import com.example.minimal_prod_backend.entity.Role;
import com.example.minimal_prod_backend.entity.User;
import com.example.minimal_prod_backend.exception.InvalidCredentialsException;
import com.example.minimal_prod_backend.repository.UserRepository;
import com.example.minimal_prod_backend.security.JwtUtil;
import com.example.minimal_prod_backend.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest rq) {
        User user = userRepository.findByUsername(rq.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(rq.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        var roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        String token = jwtUtil.generateToken(user.getUsername(), roles);

        return new LoginResponse(token);
    }
}
