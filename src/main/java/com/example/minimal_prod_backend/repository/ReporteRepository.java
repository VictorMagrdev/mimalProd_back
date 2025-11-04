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

    public ReporteRepository(EntityManager em) {
        this.em = em;
    }

    public List<IndicadorProductividadDTO> obtenerProductividad() {
        return em.createQuery("""
            SELECT new com.example.minimal_prod_backend.dto.IndicadorProductividadDTO(
                o.id,
                p.nombre,
                SUM(l.cantidadProducida),
                SUM(l.horasTrabajadas),
                COALESCE(SUM(l.cantidadProducida)/NULLIF(SUM(l.horasTrabajadas),0)*100,0)
            )
            FROM ordenes_produccion o
            JOIN o.producto p
            JOIN o.lineas l
            GROUP BY o.id, p.nombre
        """, IndicadorProductividadDTO.class).getResultList();
    }

    public List<ReporteCostoOrdenDTO> obtenerCostosPorOrden() {
        return em.createQuery("""
            SELECT new com.example.minimal_prod_backend.dto.ReporteCostoOrdenDTO(
                o.id,
                p.nombre,
                SUM(c.material),
                SUM(c.manoObra),
                SUM(c.indirectos),
                SUM(c.material + c.manoObra + c.indirectos)
            )
            FROM Costo c
            JOIN c.orden o
            JOIN o.producto p
            GROUP BY o.id, p.nombre
        """, ReporteCostoOrdenDTO.class).getResultList();
    }

    public List<ReporteCostoMaterialDTO> obtenerCostosPorMaterial() {
        return em.createQuery("""
            SELECT new com.example.minimal_prod_backend.dto.ReporteCostoMaterialDTO(
                m.nombre,
                SUM(cm.cantidad),
                AVG(cm.costoUnitario),
                SUM(cm.cantidad * cm.costoUnitario)
            )
            FROM ConsumoMaterial cm
            JOIN cm.material m
            GROUP BY m.nombre
        """, ReporteCostoMaterialDTO.class).getResultList();
    }
}
