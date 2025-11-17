package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.dto.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DashboardRepository {

    private final JdbcTemplate jdbcTemplate;

    public DashboardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ==== PRODUCCIÓN ====
    public ProduccionKPIDTO obtenerKPIsProduccion() {
        String sql = """
            SELECT
                COUNT(*) FILTER (WHERE estado_id IN (
                    SELECT id FROM estados_orden WHERE codigo = 'FINALIZADA'
                )) AS ordenes_finalizadas,
                ROUND(SUM(cantidad_producida) / NULLIF(SUM(EXTRACT(EPOCH FROM (fin_real - inicio_real)) / 3600), 0), 2) AS eficiencia,
                ROUND(SUM(cantidad_desperdicio) / NULLIF(SUM(cantidad_producida), 0) * 100, 2) AS desperdicio,
                ROUND(AVG(EXTRACT(EPOCH FROM (fin_real - inicio_real)) / 3600), 2) AS horas_promedio
            FROM ordenes_produccion
        """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new ProduccionKPIDTO(
                        rs.getLong("ordenes_finalizadas"),
                        rs.getBigDecimal("eficiencia"),
                        rs.getBigDecimal("desperdicio"),
                        rs.getBigDecimal("horas_promedio")
                )
        );
    }

    // ==== COSTOS ====
    public CostosKPIDTO obtenerKPIsCostos() {
        String sql = """
            SELECT\s
                ROUND(SUM(monto), 2) AS total_costos,
                ROUND(AVG(monto), 2) AS promedio_orden,
                ROUND(SUM(CASE WHEN tipo_id = (SELECT id FROM tipos_costo WHERE codigo = 'MATERIAL') THEN monto ELSE 0 END), 2) AS materiales,
                ROUND(SUM(CASE WHEN tipo_id = (SELECT id FROM tipos_costo WHERE codigo = 'MANO_OBRA') THEN monto ELSE 0 END), 2) AS mano_obra,
                ROUND(SUM(CASE WHEN tipo_id = (SELECT id FROM tipos_costo WHERE codigo = 'INDIRECTO') THEN monto ELSE 0 END), 2) AS indirectos
            FROM costos_orden
       \s""";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new CostosKPIDTO(
                        rs.getBigDecimal("total_costos"),
                        rs.getBigDecimal("promedio_orden"),
                        rs.getBigDecimal("materiales"),
                        rs.getBigDecimal("mano_obra"),
                        rs.getBigDecimal("indirectos")
                )
        );
    }

    // ==== TIEMPOS ====
    public TiemposKPIDTO obtenerKPIsTiempos() {
        String sql = """
            SELECT
                ROUND(SUM(EXTRACT(EPOCH FROM (fin_planificado - inicio_planificado)) / 3600), 2) AS horas_planificadas,
                ROUND(SUM(EXTRACT(EPOCH FROM (fin_real - inicio_real)) / 3600), 2) AS horas_reales,
                ROUND(
                    (SUM(EXTRACT(EPOCH FROM (fin_planificado - inicio_planificado)) / 3600) /
                     NULLIF(SUM(EXTRACT(EPOCH FROM (fin_real - inicio_real)) / 3600), 0)) * 100, 2
                ) AS cumplimiento
            FROM ordenes_produccion
        """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new TiemposKPIDTO(
                        rs.getBigDecimal("horas_planificadas"),
                        rs.getBigDecimal("horas_reales"),
                        rs.getBigDecimal("cumplimiento")
                )
        );
    }

    // ==== SERIES TEMPORALES ====
    public List<SerieTemporalDTO> obtenerSerieProduccionDiaria() {
        String sql = """
            SELECT DATE(inicio_real) AS fecha, COALESCE(SUM(cantidad_producida), 0) AS valor
            FROM ordenes_produccion
            WHERE inicio_real IS NOT NULL
            GROUP BY fecha
            ORDER BY fecha
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new SerieTemporalDTO(
                        rs.getDate("fecha").toLocalDate(),
                        rs.getBigDecimal("valor")
                )
        );
    }

    public List<SerieTemporalDTO> obtenerSerieCostosMensual() {
        String sql = """
            SELECT DATE_TRUNC('month', registrado_en) AS fecha, COALESCE(SUM(monto), 0) AS valor
            FROM costos_orden
            GROUP BY fecha
            ORDER BY fecha
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new SerieTemporalDTO(
                        rs.getTimestamp("fecha").toLocalDateTime().toLocalDate(),
                        rs.getBigDecimal("valor")
                )
        );
    }

    public List<SerieTemporalDTO> obtenerSerieTiemposMensual() {
        String sql = """
            SELECT DATE_TRUNC('month', inicio_real) AS fecha,
                   COALESCE(SUM(EXTRACT(EPOCH FROM (fin_real - inicio_real)) / 3600), 0) AS valor
            FROM ordenes_produccion
            WHERE inicio_real IS NOT NULL AND fin_real IS NOT NULL
            GROUP BY fecha
            ORDER BY fecha
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new SerieTemporalDTO(
                        rs.getTimestamp("fecha").toLocalDateTime().toLocalDate(),
                        rs.getBigDecimal("valor")
                )
        );
    }
}
