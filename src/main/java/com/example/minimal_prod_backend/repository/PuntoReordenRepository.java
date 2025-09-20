package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.PuntoReorden;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.entity.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PuntoReordenRepository extends JpaRepository<PuntoReorden, Long> {
    Optional<PuntoReorden> findByProductoAndBodega(Producto producto, Bodega bodega);
}
