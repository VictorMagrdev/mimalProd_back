package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.RequerimientoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequerimientoMaterialRepository extends JpaRepository<RequerimientoMaterial, Long> {
}
