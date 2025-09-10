package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.CostoOrden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostoOrdenRepository extends JpaRepository<CostoOrden, Integer> {
}
