package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.entity.TipoCosto;
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
    @PreAuthorize("@customSecurity.hasPermission('TIPO_COSTO_TAG', 'READ')")
    public List<TipoCosto> tiposCosto() {
        return tipoCostoService.getTiposCosto();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_COSTO_TAG', 'READ')")
    public TipoCosto tipoCosto(@Argument Long id) {
        return tipoCostoService.getTipoCostoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_COSTO_TAG', 'CREATE')")
    public TipoCosto createTipoCosto(@Argument TipoCosto tipoCosto) {
        return tipoCostoService.createTipoCosto(tipoCosto);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_COSTO_TAG', 'UPDATE')")
    public TipoCosto updateTipoCosto(@Argument Long id, @Argument TipoCosto tipoCosto) {
        return tipoCostoService.updateTipoCosto(id, tipoCosto);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_COSTO_TAG', 'DELETE')")
    public boolean deleteTipoCosto(@Argument Long id) {
        tipoCostoService.deleteTipoCosto(id);
        return true;
    }
}
