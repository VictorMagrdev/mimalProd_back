package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Policy;
import com.example.minimal_prod_backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    boolean existsByRole_IdInAndTag_NameAndPermission_Action(
            List<Long> roleIds,
            String tagName,
            String permissionAction
    );

    List<Policy> findByRoleIn(Collection<Role> roles);

}
