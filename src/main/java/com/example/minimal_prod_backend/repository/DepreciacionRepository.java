package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.dto.Response.DepreciacionResponse;
import com.example.minimal_prod_backend.dto.Response.IncidenciaArchivoResponse;
import com.example.minimal_prod_backend.entity.Depreciacion;
import com.example.minimal_prod_backend.entity.TipoPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DepreciacionRepository extends JpaRepository<Depreciacion, Long> {
    boolean existsByTipoPeriodoAndPeriodo(TipoPeriodo tipoPeriodo, LocalDate periodo);

    Optional<Depreciacion> findTopByMaquinaIdOrderByPeriodoDesc(Long maquinaId);

    List<Depreciacion> findByMaquinaIdOrderByPeriodoAsc(Long maquinaId);

    List<DepreciacionResponse> findByMaquina_Id(Long id);

}
