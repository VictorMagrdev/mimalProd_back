package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.TipoMovimientoInput;
import com.example.minimal_prod_backend.dto.TipoMovimientoResponse;

import java.util.List;

public interface TipoMovimientoService {
    List<TipoMovimientoResponse> getTiposMovimiento();

    TipoMovimientoResponse getTipoMovimientoById(Long id);

    TipoMovimientoResponse createTipoMovimiento(TipoMovimientoInput tipoMovimientoInput);

    TipoMovimientoResponse updateTipoMovimiento(Long id, TipoMovimientoInput tipoMovimientoInput);

    void deleteTipoMovimiento(Long id);
}
