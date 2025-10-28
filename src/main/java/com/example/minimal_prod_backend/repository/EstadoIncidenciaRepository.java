package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.EstadoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoIncidenciaRepository extends JpaRepository<EstadoIncidencia, Long> {
    boolean existsByNombre(String nombre);
}
