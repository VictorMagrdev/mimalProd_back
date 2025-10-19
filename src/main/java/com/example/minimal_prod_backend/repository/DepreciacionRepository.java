package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Depreciacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepreciacionRepository extends JpaRepository<Depreciacion,Long> {
}
