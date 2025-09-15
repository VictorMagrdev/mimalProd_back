package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.MovimientoInventarioDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoInventarioDetalleRepository extends JpaRepository<MovimientoInventarioDetalle, Long> {
}
