package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.TipoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoIncidenciaRepository extends JpaRepository<TipoIncidencia, Long> {
    boolean existsByCodigo(String codigo);
}
