package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.MovimientoInventarioRequest;
import com.example.minimal_prod_backend.dto.Response.MovimientoInventarioResponse;
import com.example.minimal_prod_backend.service.MovimientoInventarioService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoInventarioService;

    public MovimientoInventarioController(MovimientoInventarioService movimientoInventarioService) {
        this.movimientoInventarioService = movimientoInventarioService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('MOVIMIENTOS_INVENTARIO_TAG', 'READ')")
    public List<MovimientoInventarioResponse> movimientosInventario() {
        return movimientoInventarioService.getMovimientosInventario();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('MOVIMIENTOS_INVENTARIO_TAG', 'READ')")
    public MovimientoInventarioResponse movimientoInventario(@Argument Long id) {
        return movimientoInventarioService.getMovimientoInventarioById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('MOVIMIENTOS_INVENTARIO_TAG', 'CREATE')")
    public MovimientoInventarioResponse createMovimientoInventario(@Argument("input") MovimientoInventarioRequest movimientoInventarioRequest) {
        return movimientoInventarioService.createMovimientoInventario(movimientoInventarioRequest);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('MOVIMIENTOS_INVENTARIO_TAG', 'UPDATE')")
    public MovimientoInventarioResponse updateMovimientoInventario(@Argument Long id, @Argument("input") MovimientoInventarioRequest movimientoInventarioRequest) {
        return movimientoInventarioService.updateMovimientoInventario(id, movimientoInventarioRequest);
    }

}
