package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.SeguimientoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeguimientoIncidenciaRepository extends JpaRepository<SeguimientoIncidencia, Long> {
    List<SeguimientoIncidencia> findByIncidenciaIdOrderByRealizadoEnAsc(Long incidenciaId);
}
