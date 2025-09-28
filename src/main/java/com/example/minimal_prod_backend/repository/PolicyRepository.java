package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Politica;
import com.example.minimal_prod_backend.entity.Rol;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import com.example.minimal_prod_backend.entity.Tag;
import com.example.minimal_prod_backend.entity.Permiso;

public interface PolicyRepository extends JpaRepository<Politica, Long> {
    boolean existsByRole_IdInAndTag_NameIgnoreCaseAndPermission_Action(
            List<Long> roleIds,
            String tagName,
            String permissionAction
    );

    @EntityGraph(attributePaths = {"tag", "permission"})
    List<Politica> findByRoleIn(Collection<Rol> roles);

    Optional<Politica> findByRoleAndTagAndPermission(Rol role, Tag tag, Permiso permiso);
}
