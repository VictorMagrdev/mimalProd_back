package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {
}
