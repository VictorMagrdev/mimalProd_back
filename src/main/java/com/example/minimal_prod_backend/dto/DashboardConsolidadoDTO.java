package com.example.minimal_prod_backend.dto;


public record DashboardConsolidadoDTO(
        ProduccionKPIDTO produccion,
        CostosKPIDTO costos,
        TiemposKPIDTO tiempos
) {}


