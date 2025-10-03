package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.MetodoValoracionRequest;
import com.example.minimal_prod_backend.entity.MetodoValoracion;

import java.util.List;

public interface MetodoValoracionService {
    List<MetodoValoracion> findAll();

    MetodoValoracion findById(Long id);

    MetodoValoracion save(MetodoValoracionRequest input);

    MetodoValoracion update(Long id, MetodoValoracionRequest input);

    void delete(Long id);
}
