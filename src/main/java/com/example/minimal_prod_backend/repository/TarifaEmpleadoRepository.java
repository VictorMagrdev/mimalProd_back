package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.TarifaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifaEmpleadoRepository extends JpaRepository<TarifaEmpleado, Long> {
}
