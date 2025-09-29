package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignacionesRepository extends JpaRepository<Asignacion, Long> {
}
