package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.CentroCosto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroCostoRepository extends JpaRepository<CentroCosto, Long> {
}
