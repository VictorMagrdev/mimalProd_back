package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.IncidenciaArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncidenciaArchivoRepository extends JpaRepository<IncidenciaArchivo, Long> {
    List<IncidenciaArchivo> findByIncidenciaId(Long incidenciaId);
}

