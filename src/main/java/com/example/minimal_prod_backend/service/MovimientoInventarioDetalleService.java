package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.MovimientoInventarioDetalleInput;
import com.example.minimal_prod_backend.dto.MovimientoInventarioDetalleResponse;

import java.util.List;

public interface MovimientoInventarioDetalleService {
    List<MovimientoInventarioDetalleResponse> getMovimientosInventarioDetalle();

    MovimientoInventarioDetalleResponse getMovimientoInventarioDetalleById(Long id);

    MovimientoInventarioDetalleResponse createMovimientoInventarioDetalle(MovimientoInventarioDetalleInput movimientoInventarioDetalleInput);

    MovimientoInventarioDetalleResponse updateMovimientoInventarioDetalle(Long id, MovimientoInventarioDetalleInput movimientoInventarioDetalleInput);

    void deleteMovimientoInventarioDetalle(Long id);
}
