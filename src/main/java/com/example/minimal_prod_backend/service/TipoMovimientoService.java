package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.TipoMovimientoRequest;
import com.example.minimal_prod_backend.dto.TipoMovimientoResponse;

import java.util.List;

public interface TipoMovimientoService {
    List<TipoMovimientoResponse> getTiposMovimiento();

    TipoMovimientoResponse getTipoMovimientoById(Long id);

    TipoMovimientoResponse createTipoMovimiento(TipoMovimientoRequest tipoMovimientoInput);

    TipoMovimientoResponse updateTipoMovimiento(Long id, TipoMovimientoRequest tipoMovimientoInput);

    void deleteTipoMovimiento(Long id);
}
