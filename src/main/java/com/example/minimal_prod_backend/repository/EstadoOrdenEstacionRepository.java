package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.EstadoOrdenEstacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoOrdenEstacionRepository extends JpaRepository<EstadoOrdenEstacion, Long> {
}
