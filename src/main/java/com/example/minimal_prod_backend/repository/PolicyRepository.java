package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    boolean existsByRole_IdInAndTag_NameAndPermission_Name(List<Long> roleIds, String tagName, String permissionName);
}
