package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Bodega;
import com.example.minimal_prod_backend.entity.InventarioLote;
import com.example.minimal_prod_backend.entity.LoteProduccion;
import com.example.minimal_prod_backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioLoteRepository extends JpaRepository<InventarioLote, Long> {
    Optional<InventarioLote> findByProductoAndLoteAndBodega(
            Producto producto,
            LoteProduccion lote,
            Bodega bodega
    );
}
