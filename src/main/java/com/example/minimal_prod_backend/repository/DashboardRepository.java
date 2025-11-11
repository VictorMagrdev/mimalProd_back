package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.dto.CostosKPIDTO;
import com.example.minimal_prod_backend.dto.ProduccionKPIDTO;
import com.example.minimal_prod_backend.dto.SerieTemporalDTO;
import com.example.minimal_prod_backend.dto.TiemposKPIDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class DashboardRepository {

    @PersistenceContext
    private EntityManager em;

    // ==== PRODUCCIÓN ====
    public ProduccionKPIDTO obtenerKPIsProduccion() {
        Object[] row = (Object[]) em.createNativeQuery("""
            SELECT\s
                COUNT(*) FILTER (WHERE estado_id IN\s
                    (SELECT id FROM estados_orden WHERE codigo = 'FINALIZADA')) AS ordenes_finalizadas,
                ROUND(SUM(cantidad_producida)/NULLIF(SUM(EXTRACT(EPOCH FROM (fin_real - inicio_real))/3600),0),2) AS eficiencia,
                ROUND(SUM(cantidad_desperdicio)/NULLIF(SUM(cantidad_producida),0)*100,2) AS desperdicio,
                ROUND(AVG(EXTRACT(EPOCH FROM (fin_real - inicio_real))/3600),2) AS horas_promedio
            FROM ordenes_produccion
       \s""").getSingleResult();

        return new ProduccionKPIDTO(
                ((Number) row[0]).longValue(),
                (BigDecimal) row[1],
                (BigDecimal) row[2],
                (BigDecimal) row[3]
        );
    }

    // ==== COSTOS ====
    public CostosKPIDTO obtenerKPIsCostos() {
        Object[] row = (Object[]) em.createNativeQuery("""
            SELECT 
                ROUND(SUM(monto),2) AS total_costos,
                ROUND(AVG(monto),2) AS promedio_orden,
                ROUND(SUM(CASE WHEN tipo_id = (SELECT id FROM tipos_costo WHERE codigo = 'MATERIAL') THEN monto ELSE 0 END),2) AS materiales,
                ROUND(SUM(CASE WHEN tipo_id = (SELECT id FROM tipos_costo WHERE codigo = 'MANO_OBRA') THEN monto ELSE 0 END),2) AS mano_obra,
                ROUND(SUM(CASE WHEN tipo_id = (SELECT id FROM tipos_costo WHERE codigo = 'INDIRECTO') THEN monto ELSE 0 END),2) AS indirectos
            FROM costos_orden
        """).getSingleResult();

        return new CostosKPIDTO(
                (BigDecimal) row[0],
                (BigDecimal) row[1],
                (BigDecimal) row[2],
                (BigDecimal) row[3],
                (BigDecimal) row[4]
        );
    }

    // ==== TIEMPOS ====
    public TiemposKPIDTO obtenerKPIsTiempos() {
        Object[] row = (Object[]) em.createNativeQuery("""
            SELECT\s
                ROUND(SUM(EXTRACT(EPOCH FROM (fin_planificado - inicio_planificado))/3600),2) AS horas_planificadas,
                ROUND(SUM(EXTRACT(EPOCH FROM (fin_real - inicio_real))/3600),2) AS horas_reales,
                ROUND(
                    (SUM(EXTRACT(EPOCH FROM (fin_planificado - inicio_planificado))/3600) /
                     NULLIF(SUM(EXTRACT(EPOCH FROM (fin_real - inicio_real))/3600),0)) * 100, 2
                ) AS cumplimiento
            FROM ordenes_produccion
       \s""").getSingleResult();

        return new TiemposKPIDTO(
                (BigDecimal) row[0],
                (BigDecimal) row[1],
                (BigDecimal) row[2]
        );
    }

    // ==== SERIES TEMPORALES ====
    public List<SerieTemporalDTO> obtenerSerieProduccionDiaria() {
        List<Object[]> result = em.createNativeQuery("""
            SELECT DATE(inicio_real) AS fecha, SUM(cantidad_producida)
            FROM ordenes_produccion
            GROUP BY fecha
            ORDER BY fecha
        """).getResultList();

        return result.stream()
                .map(r -> new SerieTemporalDTO(
                        ((java.sql.Date) r[0]).toLocalDate(),
                        (BigDecimal) r[1]
                ))
                .collect(Collectors.toList());
    }

    public List<SerieTemporalDTO> obtenerSerieCostosMensual() {
        List<Object[]> result = em.createNativeQuery("""
        SELECT DATE_TRUNC('month', registrado_en) AS fecha, SUM(monto)
        FROM costos_orden
        GROUP BY fecha
        ORDER BY fecha
    """).getResultList();

        return result.stream()
                .map(r -> new SerieTemporalDTO(
                        ((java.sql.Date) r[0]).toLocalDate(),
                        (BigDecimal) r[1]
                ))
                .collect(Collectors.toList());
    }

    public List<SerieTemporalDTO> obtenerSerieTiemposMensual() {
        List<Object[]> result = em.createNativeQuery("""
        SELECT DATE_TRUNC('month', inicio_real) AS fecha,
               SUM(EXTRACT(EPOCH FROM (fin_real - inicio_real))/3600)
        FROM ordenes_produccion
        GROUP BY fecha
        ORDER BY fecha
    """).getResultList();

        return result.stream()
                .map(r -> new SerieTemporalDTO(
                        ((java.sql.Date) r[0]).toLocalDate(),
                        (BigDecimal) r[1]
                ))
                .collect(Collectors.toList());
    }

}

