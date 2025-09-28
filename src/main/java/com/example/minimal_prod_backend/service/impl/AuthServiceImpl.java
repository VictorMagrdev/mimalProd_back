package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.LoginRequest;
import com.example.minimal_prod_backend.dto.LoginResponse;
import com.example.minimal_prod_backend.dto.PolicyResponse;
import com.example.minimal_prod_backend.entity.Politica;
import com.example.minimal_prod_backend.entity.Rol;
import com.example.minimal_prod_backend.entity.Usuario;
import com.example.minimal_prod_backend.exception.InvalidCredentialsException;
import com.example.minimal_prod_backend.repository.PolicyRepository;
import com.example.minimal_prod_backend.repository.UserRepository;
import com.example.minimal_prod_backend.security.JwtUtil;
import com.example.minimal_prod_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PolicyRepository policyRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil,
                           PolicyRepository policyRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.policyRepository = policyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest rq) {
        Usuario usuario = userRepository.findByUsername(rq.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(rq.getPassword(), usuario.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        if (Boolean.FALSE.equals(usuario.getActive())) {
            throw new InvalidCredentialsException("User account is inactive");
        }

        var roles = usuario.getRoles();
        var roleNames = roles.stream().map(Rol::getName).collect(Collectors.toList());
        String token = jwtUtil.generateToken(usuario.getUsername(), roleNames);

        List<Politica> policies = policyRepository.findByRoleIn(roles);

        List<PolicyResponse> policyResponses = policies.stream()
                .map(p -> new PolicyResponse(
                        p.getTag().getName(),
                        p.getPermiso().getAction()
                ))
                .collect(Collectors.toList());

        return new LoginResponse(token, usuario.getUsername(), roleNames, policyResponses);
    }

}
