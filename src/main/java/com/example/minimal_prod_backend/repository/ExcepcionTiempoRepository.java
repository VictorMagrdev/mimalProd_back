package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.ExcepcionTiempo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcepcionTiempoRepository extends JpaRepository<ExcepcionTiempo,Long> {
}
