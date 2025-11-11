package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.repository.DashboardRepository;
import org.springframework.stereotype.Service;
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
}

