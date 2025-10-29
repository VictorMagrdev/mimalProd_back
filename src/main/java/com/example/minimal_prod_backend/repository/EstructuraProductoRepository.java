package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.EstructuraProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface EstructuraProductoRepository extends JpaRepository<EstructuraProducto, Long> {
    Collection<Object> findByProductoPadreId(Long productoPadreId);
}
