package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.MovimientoInventarioInput;
import com.example.minimal_prod_backend.dto.MovimientoInventarioResponse;

import java.util.List;

public interface MovimientoInventarioService {
    List<MovimientoInventarioResponse> getMovimientosInventario();
    MovimientoInventarioResponse getMovimientoInventarioById(Long id);
    MovimientoInventarioResponse createMovimientoInventario(MovimientoInventarioInput movimientoInventarioInput);
    MovimientoInventarioResponse updateMovimientoInventario(Long id, MovimientoInventarioInput movimientoInventarioInput);
    void deleteMovimientoInventario(Long id);
}
