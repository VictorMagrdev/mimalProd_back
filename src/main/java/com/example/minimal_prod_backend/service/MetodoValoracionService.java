package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.MetodoValoracionInput;
import com.example.minimal_prod_backend.entity.MetodoValoracion;

import java.util.List;

public interface MetodoValoracionService {
    List<MetodoValoracion> findAll();

    MetodoValoracion findById(Long id);

    MetodoValoracion save(MetodoValoracionInput input);

    MetodoValoracion update(Long id, MetodoValoracionInput input);

    void delete(Long id);
}
