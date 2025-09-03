package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByUsername(String username);

}