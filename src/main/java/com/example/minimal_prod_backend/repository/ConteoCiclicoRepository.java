package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.ConteoCiclico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConteoCiclicoRepository extends JpaRepository<ConteoCiclico, Long> {
}
