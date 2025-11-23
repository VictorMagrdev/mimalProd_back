package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.dto.Response.LineaOrdenResponse;
import com.example.minimal_prod_backend.entity.LineaOrden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineaOrdenRepository extends JpaRepository<LineaOrden, Long> {

    List<LineaOrdenResponse> findByOrden_Id(Long id);
}
