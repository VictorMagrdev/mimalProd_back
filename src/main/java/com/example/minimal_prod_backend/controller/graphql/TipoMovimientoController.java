package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.TipoMovimientoRequest;
import com.example.minimal_prod_backend.dto.TipoMovimientoResponse;
import com.example.minimal_prod_backend.service.TipoMovimientoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TipoMovimientoController {

    private final TipoMovimientoService tipoMovimientoService;

    public TipoMovimientoController(TipoMovimientoService tipoMovimientoService) {
        this.tipoMovimientoService = tipoMovimientoService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_MOVIMIENTO_TAG', 'READ')")
    public List<TipoMovimientoResponse> tiposMovimiento() {
        return tipoMovimientoService.getTiposMovimiento();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_MOVIMIENTO_TAG', 'READ')")
    public TipoMovimientoResponse tipoMovimiento(@Argument Long id) {
        return tipoMovimientoService.getTipoMovimientoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_MOVIMIENTO_TAG', 'CREATE')")
    public TipoMovimientoResponse createTipoMovimiento(@Argument("input") TipoMovimientoRequest tipoMovimientoInput) {
        return tipoMovimientoService.createTipoMovimiento(tipoMovimientoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_MOVIMIENTO_TAG', 'UPDATE')")
    public TipoMovimientoResponse updateTipoMovimiento(@Argument Long id, @Argument("input") TipoMovimientoRequest tipoMovimientoInput) {
        return tipoMovimientoService.updateTipoMovimiento(id, tipoMovimientoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_MOVIMIENTO_TAG', 'DELETE')")
    public boolean deleteTipoMovimiento(@Argument Long id) {
        tipoMovimientoService.deleteTipoMovimiento(id);
        return true;
    }
}
