package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.EstructuraProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface EstructuraProductoRepository extends JpaRepository<EstructuraProducto, Long> {
    List<EstructuraProducto> findByProductoPadreId(Long productoPadreId);
}
