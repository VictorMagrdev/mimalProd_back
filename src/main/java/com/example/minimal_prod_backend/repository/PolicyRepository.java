package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Policy;
import com.example.minimal_prod_backend.entity.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import com.example.minimal_prod_backend.entity.Tag;
import com.example.minimal_prod_backend.entity.Permission;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    boolean existsByRole_IdInAndTag_NameIgnoreCaseAndPermission_Action(
            List<Long> roleIds,
            String tagName,
            String permissionAction
    );

    @EntityGraph(attributePaths = {"tag", "permission"})
    List<Policy> findByRoleIn(Collection<Role> roles);

    Optional<Policy> findByRoleAndTagAndPermission(Role role, Tag tag, Permission permission);
}
