package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.EstacionProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.EstacionProduccionResponse;

import java.util.List;

public interface EstacionProduccionService {
    List<EstacionProduccionResponse> getEstacionesProduccion();

    EstacionProduccionResponse getEstacionProduccionById(Long id);

    EstacionProduccionResponse createEstacionProduccion(EstacionProduccionRequest estacionProduccionInput);

    EstacionProduccionResponse updateEstacionProduccion(Long id, EstacionProduccionRequest estacionProduccionInput);

    void deleteEstacionProduccion(Long id);
}
