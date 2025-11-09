package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.dto.IndicadorProductividadDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoMaterialDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoOrdenDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ReporteRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Productividad por orden: unidades producidas, horas trabajadas (por registros_tiempo), eficiencia = unidades / horas
     */
    public List<IndicadorProductividadDTO> obtenerProductividad() {
        return em.createQuery("""
                     SELECT new com.example.minimal_prod_backend.dto.IndicadorProductividadDTO(
                         o.id,
                         p.nombre,
                         COALESCE(SUM(o.cantidadProducida), 0),
                         COALESCE(SUM(EXTRACT(EPOCH FROM rt.duracion) / 3600), 0),
                         COALESCE(
                             CASE\s
                                 WHEN SUM(EXTRACT(EPOCH FROM rt.duracion)) = 0 THEN 0
                                 ELSE (SUM(o.cantidadProducida) / NULLIF(SUM(EXTRACT(EPOCH FROM rt.duracion) / 3600), 0)) * 100
                             END,
                             0
                         )
                     )
                     FROM OrdenProduccion o
                     JOIN o.producto p
                     LEFT JOIN RegistroTiempo rt ON rt.asignacion.ordenTrabajo.referencia = o.numeroOrden
                     GROUP BY o.id, p.nombre
                \s""", IndicadorProductividadDTO.class).getResultList();
    }

    /**
     * Costos totales por orden: suma de materiales, mano de obra y otros indirectos
     */
    public List<ReporteCostoOrdenDTO> obtenerCostosPorOrden() {
        return em.createQuery("""
                    SELECT new com.example.minimal_prod_backend.dto.ReporteCostoOrdenDTO(
                        o.id,
                        p.nombre,
                        COALESCE(SUM(CASE WHEN tc.nombre ILIKE '%material%' THEN c.monto ELSE 0 END), 0),
                        COALESCE(SUM(CASE WHEN tc.nombre ILIKE '%mano%' THEN c.monto ELSE 0 END), 0),
                        COALESCE(SUM(CASE WHEN tc.nombre ILIKE '%indirect%' THEN c.monto ELSE 0 END), 0),
                        COALESCE(SUM(c.monto), 0)
                    )
                    FROM CostoOrden c
                    JOIN c.orden o
                    JOIN o.producto p
                    JOIN c.tipoCosto tc
                    GROUP BY o.id, p.nombre
                """, ReporteCostoOrdenDTO.class).getResultList();
    }

    /**
     * Costos por material (líneas de orden)
     */
    public List<ReporteCostoMaterialDTO> obtenerCostosPorMaterial() {
        return em.createQuery("""
                    SELECT new com.example.minimal_prod_backend.dto.ReporteCostoMaterialDTO(
                        p.nombre,
                        COALESCE(SUM(l.cantidadRequerida), 0),
                        COALESCE(AVG(l.costoUnitario), 0),
                        COALESCE(SUM(l.costoTotal), 0)
                    )
                    FROM LineaOrden l
                    JOIN l.productoComponente p
                    GROUP BY p.nombre
                """, ReporteCostoMaterialDTO.class).getResultList();
    }
}
