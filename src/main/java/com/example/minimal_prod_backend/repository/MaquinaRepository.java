package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaquinaRepository extends JpaRepository<Maquina, Long> {
    List<Maquina> findByActivaTrue();

    Optional<Maquina> findByCodigo(String codigo);
}