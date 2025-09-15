package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.OrdenEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenEventoRepository extends JpaRepository<OrdenEvento, Long> {
}
