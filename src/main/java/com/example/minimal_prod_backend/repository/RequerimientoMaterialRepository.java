package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.RequerimientoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface RequerimientoMaterialRepository extends JpaRepository<RequerimientoMaterial, Long> {
    List<RequerimientoMaterial> findByProductoId(Long productoId);
    List<RequerimientoMaterial> findByOrdenProduccionId(Long ordenProduccionId);
    @Query(value = "SELECT cantidad_a_pedir FROM requerimientos_materiales WHERE id = :id", nativeQuery = true)
    BigDecimal getCantidadAPedirCalculada(@Param("id") Long id);
}
