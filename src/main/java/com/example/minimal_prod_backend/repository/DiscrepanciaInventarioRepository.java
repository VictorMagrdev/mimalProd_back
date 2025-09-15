package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.DiscrepanciaInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscrepanciaInventarioRepository extends JpaRepository<DiscrepanciaInventario, Long> {
}
