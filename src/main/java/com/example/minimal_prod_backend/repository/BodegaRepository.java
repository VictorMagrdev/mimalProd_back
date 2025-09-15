package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {
}
