package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.TipoOrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.Response.TipoOrdenTrabajoResponse;

import java.util.List;

public interface TipoOrdenTrabajoService {
    List<TipoOrdenTrabajoResponse> getTiposOrdenTrabajo();

    TipoOrdenTrabajoResponse getTipoOrdenTrabajoById(Long id);

    TipoOrdenTrabajoResponse createTipoOrdenTrabajo(TipoOrdenTrabajoRequest tipoOrdenTrabajoInput);

    TipoOrdenTrabajoResponse updateTipoOrdenTrabajo(Long id, TipoOrdenTrabajoRequest tipoOrdenTrabajoInput);

    void deleteTipoOrdenTrabajo(Long id);
}
