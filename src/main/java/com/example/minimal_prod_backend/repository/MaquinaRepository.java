package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaquinaRepository extends JpaRepository<Maquina, Long> {
}