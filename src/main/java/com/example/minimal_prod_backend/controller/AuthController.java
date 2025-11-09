package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.Request.LoginRequest;
import com.example.minimal_prod_backend.dto.Response.LoginResponse;
import com.example.minimal_prod_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest rq) {
        return ResponseEntity.ok(authService.login(rq));
    }
}
