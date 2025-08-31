package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
