package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.service.DashboardService;
import org.springframework.web.bind.annotation.*;

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

    // 🔹 Serie temporal de producción (para gráficos)
    @GetMapping("/produccion/serie")
    public List<SerieTemporalDTO> getSerieProduccion() {
        return service.obtenerSerieProduccion();
    }

    // 🔹 Serie temporal de costos (para gráficos)
    @GetMapping("/costos/serie")
    public List<SerieTemporalDTO> getSerieCostos() {
        return service.obtenerSerieCostos();
    }

    // 🔹 Serie temporal de tiempos (para gráficos)
    @GetMapping("/tiempos/serie")
    public List<SerieTemporalDTO> getSerieTiempos() {
        return service.obtenerSerieTiempos();
    }

    // 🔹 Serie temporal de costos (agrupado por mes)
    @GetMapping("/costos/serie-mensual")
    public List<SerieTemporalDTO> getSerieCostosMensual() {
        return service.obtenerSerieCostosMensual();
    }

    // 🔹 Serie temporal de tiempos (agrupado por mes)
    @GetMapping("/tiempos/serie-mensual")
    public List<SerieTemporalDTO> getSerieTiemposMensual() {
        return service.obtenerSerieTiemposMensual();
    }

}
