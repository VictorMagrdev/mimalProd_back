package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    @EntityGraph(attributePaths = "roles")
    Optional<Usuario> findByUsername(String username);


}