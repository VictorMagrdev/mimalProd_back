package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.EstadoAprobacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoAprobacionRepository extends JpaRepository<EstadoAprobacion, Long> {
}

