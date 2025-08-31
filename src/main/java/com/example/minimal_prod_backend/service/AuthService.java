package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.LoginRequest;
import com.example.minimal_prod_backend.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
