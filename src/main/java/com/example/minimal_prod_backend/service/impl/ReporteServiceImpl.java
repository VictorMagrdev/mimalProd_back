package com.example.minimal_prod_backend.service.impl;


import com.example.minimal_prod_backend.dto.IndicadorProductividadDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoMaterialDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoOrdenDTO;
import com.example.minimal_prod_backend.repository.ReporteRepository;
import com.example.minimal_prod_backend.service.ReporteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository repo;

    public ReporteServiceImpl(ReporteRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<IndicadorProductividadDTO> obtenerProductividad() {
        return repo.obtenerProductividad();
    }

    @Override
    public List<ReporteCostoOrdenDTO> obtenerCostosPorOrden() {
        return repo.obtenerCostosPorOrden();
    }

    @Override
    public List<ReporteCostoMaterialDTO> obtenerCostosPorMaterial() {
        return repo.obtenerCostosPorMaterial();
    }
}
