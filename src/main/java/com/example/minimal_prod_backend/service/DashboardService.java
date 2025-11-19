package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.repository.DashboardRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    private final DashboardRepository repo;

    public DashboardService(DashboardRepository repo) {
        this.repo = repo;
    }

    public DashboardConsolidadoDTO obtenerDashboardConsolidado() {
        return new DashboardConsolidadoDTO(
                repo.obtenerKPIsProduccion(),
                repo.obtenerKPIsCostos(),
                repo.obtenerKPIsTiempos()
        );
    }

    public List<SerieTemporalDTO> obtenerSerieProduccion() {
        return repo.obtenerSerieProduccionDiaria();
    }

    public List<SerieTemporalDTO> obtenerSerieCostos() {
        return repo.obtenerSerieCostosMensual();
    }

    public List<SerieTemporalDTO> obtenerSerieTiempos() {
        return repo.obtenerSerieTiemposMensual();
    }

    public List<SerieTemporalDTO> obtenerSerieCostosMensual() {
        return repo.obtenerSerieCostosMensual();
    }

    public List<SerieTemporalDTO> obtenerSerieTiemposMensual() {
        return repo.obtenerSerieTiemposMensual();
    }

    public List<SerieProduccion7DiasDTO> obtenerProduccion7Dias() {
        return repo.obtenerProduccion7Dias();
    }

    public List<TopProductoDTO> obtenerTopProductos30Dias() {
        return repo.obtenerTopProductos30Dias();
    }

    public List<EstadoCantidadDTO> obtenerOrdenesPorEstado() {
        return repo.obtenerOrdenesPorEstado();
    }

    public Long obtenerOrdenesAtrasadasHoy() {
        return repo.obtenerOrdenesAtrasadasHoy();
    }

    public BigDecimal obtenerProduccionHoy() {
        return repo.obtenerProduccionHoy();
    }

    public BigDecimal obtenerCumplimientoHoy() {
        return repo.obtenerCumplimientoHoy();
    }
}

