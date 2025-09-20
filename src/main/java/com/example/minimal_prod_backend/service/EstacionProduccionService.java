package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.EstacionProduccionInput;
import com.example.minimal_prod_backend.dto.EstacionProduccionResponse;

import java.util.List;

public interface EstacionProduccionService {
    List<EstacionProduccionResponse> getEstacionesProduccion();

    EstacionProduccionResponse getEstacionProduccionById(Long id);

    EstacionProduccionResponse createEstacionProduccion(EstacionProduccionInput estacionProduccionInput);

    EstacionProduccionResponse updateEstacionProduccion(Long id, EstacionProduccionInput estacionProduccionInput);

    void deleteEstacionProduccion(Long id);
}
