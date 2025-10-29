package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.OrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.OrdenTrabajoResponse;

import java.util.List;

public interface OrdenTrabajoService {
    List<OrdenTrabajoResponse> findAll();

    OrdenTrabajoResponse findById(Long id);

    OrdenTrabajoResponse save(OrdenTrabajoRequest ordenTrabajoRequest);

    void deleteById(Long id);
}
