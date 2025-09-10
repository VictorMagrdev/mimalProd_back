package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.EstadoOrden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoOrdenRepository extends JpaRepository<EstadoOrden, Integer> {
}
