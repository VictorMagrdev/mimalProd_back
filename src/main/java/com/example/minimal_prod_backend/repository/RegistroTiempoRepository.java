package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.RegistroTiempo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroTiempoRepository extends JpaRepository<RegistroTiempo,Long> {
}
