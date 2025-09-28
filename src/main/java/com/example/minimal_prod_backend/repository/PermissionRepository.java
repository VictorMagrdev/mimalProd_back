package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permiso,Long> {
    Optional<Permiso> findByAction(String action);
}
