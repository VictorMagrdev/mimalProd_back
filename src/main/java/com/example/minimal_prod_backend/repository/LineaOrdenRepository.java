package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.LineaOrden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineaOrdenRepository extends JpaRepository<LineaOrden, Long> {
}
