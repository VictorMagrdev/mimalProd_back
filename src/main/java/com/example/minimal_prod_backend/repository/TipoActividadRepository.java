package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.TipoActividad;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoActividadRepository extends JpaRepository<TipoActividad, Long> {
}
