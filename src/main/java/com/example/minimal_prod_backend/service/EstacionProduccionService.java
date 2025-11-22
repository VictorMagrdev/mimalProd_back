package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.EstacionProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.EstacionProduccionResponse;
import com.example.minimal_prod_backend.entity.EstacionProduccion;

import java.util.List;
import java.util.Optional;

public interface EstacionProduccionService {
    List<EstacionProduccionResponse> getEstacionesProduccion();

    EstacionProduccionResponse getEstacionProduccionById(Long id);

    EstacionProduccionResponse createEstacionProduccion(EstacionProduccionRequest estacionProduccionInput);

    EstacionProduccionResponse updateEstacionProduccion(Long id, EstacionProduccionRequest estacionProduccionInput);

    void deleteEstacionProduccion(Long id);

    Optional<EstacionProduccion> findById(Long id);
}
