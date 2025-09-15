package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.EstacionProduccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionProduccionRepository extends JpaRepository<EstacionProduccion, Long> {
}
