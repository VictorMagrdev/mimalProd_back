package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.OperacionOrdenEstadoRequest;
import com.example.minimal_prod_backend.dto.OperacionOrdenEstadoResponse;

import java.util.List;

public interface OperacionOrdenEstadoService {
    List<OperacionOrdenEstadoResponse> findAll();
    OperacionOrdenEstadoResponse findById(Long id);
    OperacionOrdenEstadoResponse save(OperacionOrdenEstadoRequest operacionOrdenEstadoRequest);
    void deleteById(Long id);
}
