package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Integer> {
}
