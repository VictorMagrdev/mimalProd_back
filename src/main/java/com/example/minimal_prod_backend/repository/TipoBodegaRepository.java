package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.TipoBodega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoBodegaRepository extends JpaRepository<TipoBodega, Long> {
}
