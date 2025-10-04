package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.EstadoOrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstadoOrdenTrabajoRepository extends JpaRepository<EstadoOrdenTrabajo, Long> {


}