package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Long> {

    Optional<TipoMovimiento> findByNombre(String nombre);

}
