package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.EstadoOrden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoOrdenRepository extends JpaRepository<EstadoOrden, Long> {

    Optional<EstadoOrden> findByNombre(String nombre);

}
