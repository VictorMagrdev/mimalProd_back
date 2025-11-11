package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.dto.IndicadorProductividadDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoMaterialDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoOrdenDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReporteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReporteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<IndicadorProductividadDTO> obtenerProductividad() {
        String sql = """
            SELECT
              o.id as orden_id,
              p.nombre as producto,
              COALESCE(SUM(o.cantidad_producida), 0) AS unidades_producidas,
              COALESCE(SUM(EXTRACT(EPOCH FROM (o.fin_real - o.inicio_real)) / 3600.0), 0) AS horas_trabajadas,
              CASE
                  WHEN COALESCE(SUM(EXTRACT(EPOCH FROM (o.fin_real - o.inicio_real)) / 3600.0), 0) = 0
                      THEN 0
                  ELSE COALESCE(SUM(o.cantidad_producida), 0) /
                       COALESCE(SUM(EXTRACT(EPOCH FROM (o.fin_real - o.inicio_real)) / 3600.0), 1)
              END AS eficiencia
            FROM ordenes_produccion o
            JOIN ordenes_lotes ol ON ol.orden_id = o.id
            JOIN lotes_produccion l ON ol.lote_id = l.id
            JOIN productos p ON l.producto_id = p.id
            WHERE o.inicio_real IS NOT NULL AND o.fin_real IS NOT NULL
            GROUP BY o.id, p.nombre
            """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new IndicadorProductividadDTO(
                        rs.getLong("orden_id"),
                        rs.getString("producto"),
                        rs.getBigDecimal("unidades_producidas"),
                        rs.getBigDecimal("horas_trabajadas"),
                        rs.getBigDecimal("eficiencia")
                )
        );
    }

    public List<ReporteCostoOrdenDTO> obtenerCostosPorOrden() {
        String sql = """
            SELECT
              o.id as orden_id,
              p.nombre as producto,
              COALESCE(SUM(CASE WHEN tc.nombre ILIKE '%material%' THEN c.monto ELSE 0 END), 0) AS costo_material,
              COALESCE(SUM(CASE WHEN tc.nombre ILIKE '%mano%' THEN c.monto ELSE 0 END), 0) AS costo_mano_obra,
              COALESCE(SUM(CASE WHEN tc.nombre ILIKE '%indirect%' THEN c.monto ELSE 0 END), 0) AS costo_indirecto,
              COALESCE(SUM(c.monto), 0) AS total
            FROM costos_orden c
            JOIN ordenes_produccion o ON c.orden_id = o.id
            JOIN ordenes_lotes ol ON ol.orden_id = o.id
            JOIN lotes_produccion l ON ol.lote_id = l.id
            JOIN productos p ON l.producto_id = p.id
            JOIN tipo_costo tc ON c.tipo_costo_id = tc.id
            GROUP BY o.id, p.nombre
            """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new ReporteCostoOrdenDTO(
                        rs.getLong("orden_id"),
                        rs.getString("producto"),
                        rs.getBigDecimal("costo_material"),
                        rs.getBigDecimal("costo_mano_obra"),
                        rs.getBigDecimal("costo_indirecto"),
                        rs.getBigDecimal("total")
                )
        );
    }

    public List<ReporteCostoMaterialDTO> obtenerCostosPorMaterial() {
        String sql = """
            SELECT
              p.nombre as material,
              COALESCE(SUM(l.cantidad_requerida), 0) AS cantidad,
              COALESCE(AVG(l.costo_unitario), 0) AS costo_unitario,
              COALESCE(SUM(l.costo_total), 0) AS costo_total
            FROM lineas_orden l
            JOIN productos p ON l.producto_componente_id = p.id
            GROUP BY p.nombre
            """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new ReporteCostoMaterialDTO(
                        rs.getString("material"),
                        rs.getBigDecimal("cantidad"),
                        rs.getBigDecimal("costo_unitario"),
                        rs.getBigDecimal("costo_total")
                )
        );
    }
}