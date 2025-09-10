package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
