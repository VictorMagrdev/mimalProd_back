package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.TipoCosto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCostoRepository extends JpaRepository<TipoCosto, Long> {
}
