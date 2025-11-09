package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.MovimientoInventarioDetalleRequest;
import com.example.minimal_prod_backend.dto.Response.MovimientoInventarioDetalleResponse;

import java.util.List;

public interface MovimientoInventarioDetalleService {
    List<MovimientoInventarioDetalleResponse> getMovimientosInventarioDetalle();

    MovimientoInventarioDetalleResponse getMovimientoInventarioDetalleById(Long id);

    MovimientoInventarioDetalleResponse createMovimientoInventarioDetalle(MovimientoInventarioDetalleRequest movimientoInventarioDetalleInput);

    MovimientoInventarioDetalleResponse updateMovimientoInventarioDetalle(Long id, MovimientoInventarioDetalleRequest movimientoInventarioDetalleInput);

    void deleteMovimientoInventarioDetalle(Long id);
}
