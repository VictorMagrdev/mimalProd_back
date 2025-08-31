package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.LoginRequest;
import com.example.minimal_prod_backend.dto.LoginResponse;
import com.example.minimal_prod_backend.dto.RegisterRequest;
import com.example.minimal_prod_backend.entity.Role;
import com.example.minimal_prod_backend.entity.User;
import com.example.minimal_prod_backend.repository.RoleRepository;
import com.example.minimal_prod_backend.repository.UserRepository;
import com.example.minimal_prod_backend.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest r) {
        if (userRepository.findByUsername(r.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("username already exists");
        }
        User u = User.builder()
                .username(r.getUsername())
                .email(r.getEmail())
                .password(passwordEncoder.encode(r.getPassword()))
                .build();

        if (r.getRole() != null) {
            roleRepository.findByName(r.getRole()).ifPresent(role -> u.getRoles().add(role));
        }

        userRepository.save(u);
        return ResponseEntity.ok("registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest rq) {
        var opt = userRepository.findByUsername(rq.getUsername());
        if (opt.isEmpty()) return ResponseEntity.status(401).body("invalid credentials");

        var user = opt.get();
        if (!passwordEncoder.matches(rq.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("invalid credentials");
        }

        var roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        String token = jwtUtil.generateToken(user.getUsername(), roles);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}