package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.TipoCostoRequest;
import com.example.minimal_prod_backend.dto.TipoCostoResponse;
import com.example.minimal_prod_backend.service.TipoCostoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TipoCostoController {

    private final TipoCostoService tipoCostoService;

    public TipoCostoController(TipoCostoService tipoCostoService) {
        this.tipoCostoService = tipoCostoService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPOS_COSTO_TAG', 'READ')")
    public List<TipoCostoResponse> tiposCosto() {
        return tipoCostoService.getTiposCosto();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPOS_COSTO_TAG', 'READ')")
    public TipoCostoResponse tipoCosto(@Argument Long id) {
        return tipoCostoService.getTipoCostoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPOS_COSTO_TAG', 'CREATE')")
    public TipoCostoResponse createTipoCosto(@Argument("input") TipoCostoRequest tipoCostoInput) {
        return tipoCostoService.createTipoCosto(tipoCostoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPOS_COSTO_TAG', 'UPDATE')")
    public TipoCostoResponse updateTipoCosto(@Argument Long id, @Argument("input") TipoCostoRequest tipoCostoInput) {
        return tipoCostoService.updateTipoCosto(id, tipoCostoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPOS_COSTO_TAG', 'DELETE')")
    public boolean deleteTipoCosto(@Argument Long id) {
        tipoCostoService.deleteTipoCosto(id);
        return true;
    }
}
