package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.MovimientoInventarioDetalleInput;
import com.example.minimal_prod_backend.dto.MovimientoInventarioDetalleResponse;
import com.example.minimal_prod_backend.service.MovimientoInventarioDetalleService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MovimientoInventarioDetalleController {

    private final MovimientoInventarioDetalleService movimientoInventarioDetalleService;

    public MovimientoInventarioDetalleController(MovimientoInventarioDetalleService movimientoInventarioDetalleService) {
        this.movimientoInventarioDetalleService = movimientoInventarioDetalleService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('MOVIMIENTO_INVENTARIO_DETALLE_TAG', 'READ')")
    public List<MovimientoInventarioDetalleResponse> movimientosInventarioDetalle() {
        return movimientoInventarioDetalleService.getMovimientosInventarioDetalle();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('MOVIMIENTO_INVENTARIO_DETALLE_TAG', 'READ')")
    public MovimientoInventarioDetalleResponse movimientoInventarioDetalle(@Argument Long id) {
        return movimientoInventarioDetalleService.getMovimientoInventarioDetalleById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('MOVIMIENTO_INVENTARIO_DETALLE_TAG', 'CREATE')")
    public MovimientoInventarioDetalleResponse createMovimientoInventarioDetalle(@Argument("input") MovimientoInventarioDetalleInput movimientoInventarioDetalleInput) {
        return movimientoInventarioDetalleService.createMovimientoInventarioDetalle(movimientoInventarioDetalleInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('MOVIMIENTO_INVENTARIO_DETALLE_TAG', 'UPDATE')")
    public MovimientoInventarioDetalleResponse updateMovimientoInventarioDetalle(@Argument Long id, @Argument("input") MovimientoInventarioDetalleInput movimientoInventarioDetalleInput) {
        return movimientoInventarioDetalleService.updateMovimientoInventarioDetalle(id, movimientoInventarioDetalleInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('MOVIMIENTO_INVENTARIO_DETALLE_TAG', 'DELETE')")
    public boolean deleteMovimientoInventarioDetalle(@Argument Long id) {
        movimientoInventarioDetalleService.deleteMovimientoInventarioDetalle(id);
        return true;
    }
}
