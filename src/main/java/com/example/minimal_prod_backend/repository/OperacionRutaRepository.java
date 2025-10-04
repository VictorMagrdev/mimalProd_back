package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.OperacionRuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacionRutaRepository extends JpaRepository<OperacionRuta, Long> {
}
