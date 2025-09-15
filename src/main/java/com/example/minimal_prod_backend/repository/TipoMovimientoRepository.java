package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Long> {
}
