package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.FuncionTarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionTareaRepository extends JpaRepository<FuncionTarea, Long> {
}
