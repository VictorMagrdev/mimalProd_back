package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
}
