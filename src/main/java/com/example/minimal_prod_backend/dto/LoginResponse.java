package com.example.minimal_prod_backend.dto;

import java.util.List;

public record LoginResponse(
        String token,
        String username,
        List<String> roles,
        List<PoliticaLoginResponse> politicas
) {}