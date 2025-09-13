package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.LoteProduccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteProduccionRepository extends JpaRepository<LoteProduccion, Long> {
}
