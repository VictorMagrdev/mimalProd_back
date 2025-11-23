package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.dto.Response.CostoOrdenResponse;
import com.example.minimal_prod_backend.entity.CostoOrden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostoOrdenRepository extends JpaRepository<CostoOrden, Long> {

    List<CostoOrdenResponse> findByOrden_Id(Long id);
}
