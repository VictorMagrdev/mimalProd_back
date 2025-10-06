package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Permiso;
import com.example.minimal_prod_backend.entity.Politica;
import com.example.minimal_prod_backend.entity.Rol;
import com.example.minimal_prod_backend.entity.Tag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Politica, Long> {
    boolean existsByRol_IdInAndTag_NombreIgnoreCaseAndPermiso_Accion(
            List<Long> roleIds,
            String tagNombre,
            String permisoAccion
    );

    @EntityGraph(attributePaths = {"tag", "permission"})
    List<Politica> findByRolIn(Collection<Rol> roles);

    Optional<Politica> findByRolAndTagAndPermiso(Rol role, Tag tag, Permiso permiso);
}
