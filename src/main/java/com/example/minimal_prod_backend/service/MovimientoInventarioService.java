package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.MovimientoInventarioRequest;
import com.example.minimal_prod_backend.dto.Response.MovimientoInventarioResponse;

import java.util.List;

public interface MovimientoInventarioService {

    List<MovimientoInventarioResponse> getMovimientosInventario();

    MovimientoInventarioResponse getMovimientoInventarioById(Long id);

    MovimientoInventarioResponse createMovimientoInventario(MovimientoInventarioRequest movimientoInventarioRequest);

    MovimientoInventarioResponse updateMovimientoInventario(Long id, MovimientoInventarioRequest movimientoInventarioRequest);

}
