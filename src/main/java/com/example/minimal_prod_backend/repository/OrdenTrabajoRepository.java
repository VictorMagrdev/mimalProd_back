package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.OrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo, Long> {
}
