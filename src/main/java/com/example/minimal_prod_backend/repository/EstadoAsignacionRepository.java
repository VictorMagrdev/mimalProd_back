package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.EstadoAsignacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoAsignacionRepository extends JpaRepository<EstadoAsignacion, Long> {
}
