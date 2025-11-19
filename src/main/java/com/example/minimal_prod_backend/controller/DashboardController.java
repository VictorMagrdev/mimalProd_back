package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/consolidado")
    public DashboardConsolidadoDTO getConsolidado() {
        return service.obtenerDashboardConsolidado();
    }

    //  Serie temporal de producción (para gráficos)
    @GetMapping("/produccion/serie")
    public List<SerieTemporalDTO> getSerieProduccion() {
        return service.obtenerSerieProduccion();
    }

    //  Serie temporal de costos (para gráficos)
    @GetMapping("/costos/serie")
    public List<SerieTemporalDTO> getSerieCostos() {
        return service.obtenerSerieCostos();
    }

    //  Serie temporal de tiempos (para gráficos)
    @GetMapping("/tiempos/serie")
    public List<SerieTemporalDTO> getSerieTiempos() {
        return service.obtenerSerieTiempos();
    }

    //  Serie temporal de costos (agrupado por mes)
    @GetMapping("/costos/serie-mensual")
    public List<SerieTemporalDTO> getSerieCostosMensual() {
        return service.obtenerSerieCostosMensual();
    }

    //  Serie temporal de tiempos (agrupado por mes)
    @GetMapping("/tiempos/serie-mensual")
    public List<SerieTemporalDTO> getSerieTiemposMensual() {
        return service.obtenerSerieTiemposMensual();
    }

    //para el index
    //  Producción últimos 7 días
    @GetMapping("/produccion/7-dias")
    public List<SerieProduccion7DiasDTO> getProduccion7Dias() {
        return service.obtenerProduccion7Dias();
    }

    //  Top productos últimos 30 días
    @GetMapping("/productos/top-30-dias")
    public List<TopProductoDTO> getTopProductos30Dias() {
        return service.obtenerTopProductos30Dias();
    }

    //  Cantidad de órdenes por estado
    @GetMapping("/ordenes/por-estado")
    public List<EstadoCantidadDTO> getOrdenesPorEstado() {
        return service.obtenerOrdenesPorEstado();
    }

    //  Órdenes atrasadas hoy
    @GetMapping("/ordenes/atrasadas-hoy")
    public Long getOrdenesAtrasadasHoy() {
        return service.obtenerOrdenesAtrasadasHoy();
    }

    //  Producción de hoy
    @GetMapping("/produccion/hoy")
    public BigDecimal getProduccionHoy() {
        return service.obtenerProduccionHoy();
    }

    //  Cumplimiento de hoy
    @GetMapping("/cumplimiento/hoy")
    public BigDecimal getCumplimientoHoy() {
        return service.obtenerCumplimientoHoy();
    }
}
