package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.OrdenProduccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenProduccionRepository extends JpaRepository<OrdenProduccion, Long> {
}
