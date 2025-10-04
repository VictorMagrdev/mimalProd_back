package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.TipoOrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoOrdenTrabajoRepository extends JpaRepository<TipoOrdenTrabajo, Long> {
}
