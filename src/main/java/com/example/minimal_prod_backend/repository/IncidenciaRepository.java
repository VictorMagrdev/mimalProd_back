package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    boolean existsByCodigo(String codigo);
}

