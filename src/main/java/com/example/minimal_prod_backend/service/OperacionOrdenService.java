package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.OperacionOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.OperacionOrdenResponse;

import java.util.List;

public interface OperacionOrdenService {
    List<OperacionOrdenResponse> findAll();

    OperacionOrdenResponse findById(Long id);

    OperacionOrdenResponse save(OperacionOrdenRequest operacionOrdenRequest);

    void deleteById(Long id);
}
