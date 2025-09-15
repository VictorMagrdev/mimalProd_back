package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.ReservaMaterialOrden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaMaterialOrdenRepository extends JpaRepository<ReservaMaterialOrden, Long> {
}
