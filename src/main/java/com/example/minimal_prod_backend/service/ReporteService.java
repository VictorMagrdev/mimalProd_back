package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.IndicadorProductividadDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoMaterialDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoOrdenDTO;

import java.util.List;

public interface ReporteService {
    List<IndicadorProductividadDTO> obtenerProductividad();

    List<ReporteCostoOrdenDTO> obtenerCostosPorOrden();

    List<ReporteCostoMaterialDTO> obtenerCostosPorMaterial();
}
