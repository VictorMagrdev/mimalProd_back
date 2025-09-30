package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.TarifaEmpleadoRequest;
import com.example.minimal_prod_backend.dto.TarifaEmpleadoResponse;

import java.util.List;

public interface TarifaEmpleadoService {
    List<TarifaEmpleadoResponse> getTarifasEmpleado();

    TarifaEmpleadoResponse getTarifaEmpleadoById(Long id);

    TarifaEmpleadoResponse createTarifaEmpleado(TarifaEmpleadoRequest request);

    TarifaEmpleadoResponse updateTarifaEmpleado(Long id, TarifaEmpleadoRequest request);

    void deleteTarifaEmpleado(Long id);
}
