package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.LoginRequest;
import com.example.minimal_prod_backend.dto.Response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
