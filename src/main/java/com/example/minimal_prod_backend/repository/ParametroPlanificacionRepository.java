package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.ParametroPlanificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParametroPlanificacionRepository extends JpaRepository<ParametroPlanificacion, Long> {
    Optional<ParametroPlanificacion> findByProductoId(Long productoId);
}
